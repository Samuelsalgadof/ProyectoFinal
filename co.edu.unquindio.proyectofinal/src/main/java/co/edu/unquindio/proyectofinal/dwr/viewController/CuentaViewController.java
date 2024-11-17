package co.edu.unquindio.proyectofinal.dwr.viewController;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.unquindio.proyectofinal.dwr.Controller.CuentaController;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;
import co.edu.unquindio.proyectofinal.dwr.model.Cuenta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CuentaViewController {
    ObservableList<CuentaDto> cuentaDtos = FXCollections.observableArrayList();
    CuentaController cuentaController;
    CuentaDto cuentaSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<CuentaDto, String> colBanco;

    @FXML
    private TableColumn<CuentaDto, String> colNumero;

    @FXML
    private TableColumn<CuentaDto, String> colIDCuenta;

    @FXML
    private ComboBox<String> cbTipoCuenta;

    @FXML
    private TableView<CuentaDto> colCuentas;

    @FXML
    private TableColumn<CuentaDto, String> colTipo;

    @FXML
    private Button btnAgregarCuenta;

    @FXML
    private ComboBox<String> cbBanco;

    @FXML
    private TextField txtNumeroCuenta;

    @FXML
    private Button btnActualizar;

    @FXML
    void onNuevaCuenta(ActionEvent event) {
        agregarCuenta();
    }

    private void agregarCuenta() {
        CuentaDto cuentaDto = construirCuentaDto();
        if (datosValidos()) {
            if (cuentaController.agregarCuenta(cuentaDto)) {
                cuentaDtos.add(cuentaDto);
                mostrarMensaje("Notificación Cuenta", "Cuenta creada", "La cuenta se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposCuenta();
                registrarAcciones("Cuenta agregada", 1, "Agregar Cuenta");
            } else {
                mostrarMensaje("Notificación Cuenta", "Cuenta no creada", "La cuenta no se pudo crear con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación Cuenta", "Cuenta no creada", "Los datos son inválidos", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCamposCuenta() {
        cbTipoCuenta.getSelectionModel().select("--Seleccione--");
        cbBanco.getSelectionModel().select("--Seleccione--");
        txtNumeroCuenta.setText("");
    }

    private CuentaDto construirCuentaDto() {
        return new CuentaDto("4",
                cbBanco.getValue(),
                Integer.parseInt(txtNumeroCuenta.getText()),
                cbTipoCuenta.getValue()
        );
    }

    private boolean datosValidos() {
        return !cbTipoCuenta.getValue().equalsIgnoreCase("--Seleccione--")
                && !cbBanco.getValue().equalsIgnoreCase("--Seleccione--")
                && !txtNumeroCuenta.getText().isEmpty();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        cuentaController.registrarAcciones(mensaje, nivel, accion);
    }

    @FXML
    void onEliminarCuenta(ActionEvent event) {
        eliminarCuenta();
    }

    private void eliminarCuenta() {
        boolean cuentaEliminada = false;
        if (cuentaSeleccionada != null) {
            if (mostrarMensajeConfirmacion("¿Seguro que deseas eliminar la cuenta?")) {
                cuentaEliminada = cuentaController.eliminarCuenta(String.valueOf(cuentaSeleccionada.idCuenta()));
                if (cuentaEliminada) {
                    cuentaDtos.remove(cuentaSeleccionada);
                    cuentaSeleccionada = null;
                    colCuentas.getSelectionModel().clearSelection();
                    limpiarCamposCuenta();
                    mostrarMensaje("Notificación Cuenta", "Cuenta eliminada", "Cuenta eliminada con éxito", Alert.AlertType.INFORMATION);
                } else {
                    mostrarMensaje("Notificación Cuenta", "Cuenta no eliminada", "La cuenta no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Notificación Cuenta", "Cuenta no seleccionada", "Selecciona una cuenta de la lista", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void onActualizarCuenta(ActionEvent event) {
        actualizarCuenta();
    }

    private void actualizarCuenta() {
        boolean cuentaActualizada = false;
        String idCuentaActual = String.valueOf(cuentaSeleccionada.idCuenta());
        CuentaDto cuentaDto = construirCuentaDto();
        if (cuentaSeleccionada != null) {
            if (datosValidos()) {
                cuentaActualizada = cuentaController.actualizarCuenta(idCuentaActual, cuentaDto);
                if (cuentaActualizada) {
                    cuentaDtos.remove(cuentaSeleccionada);
                    cuentaDtos.add(cuentaDto);
                    colCuentas.refresh();
                    mostrarMensaje("Notificación Cuenta", "Cuenta actualizada", "La cuenta se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposCuenta();
                } else {
                    mostrarMensaje("Notificación Cuenta", "Cuenta no actualizada", "La cuenta no se ha actualizado con éxito", Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje("Notificación Cuenta", "Cuenta no creada", "Los datos ingresados son inválidos", Alert.AlertType.ERROR);
            }
        }
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.isPresent() && action.get() == ButtonType.OK;
    }

    @FXML
    void initialize() {
        cuentaController = new CuentaController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerCuenta();
        initCombo();
        colCuentas.getItems().clear();
        colCuentas.setItems(cuentaDtos);
        listenerSelection();
    }

    private void initCombo() {
        initComboBanco();
        initComboTipo();
    }

    private void initDataBinding() {
        colNumero.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().numCuenta())));
        colBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreBanco()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoCuenta()));
        colIDCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idCuenta()));
    }

    private void obtenerCuenta() {
        cuentaDtos.addAll(cuentaController.obtenerCuentas());
    }

    private void listenerSelection() {
        colCuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cuentaSeleccionada = newSelection;
            mostrarInformacionCuenta(cuentaSeleccionada);
        });
    }

    private void mostrarInformacionCuenta(CuentaDto cuentaDto) {
        if (cuentaSeleccionada != null) {
            txtNumeroCuenta.setText(String.valueOf(cuentaSeleccionada.numCuenta()));
            cbBanco.setValue(cuentaSeleccionada.nombreBanco());
            cbTipoCuenta.setValue(cuentaSeleccionada.tipoCuenta());
        }
    }

    private void  initComboBanco(){
        ObservableList<String> cbBancos = FXCollections.observableArrayList();
        cbBancos.addAll("--Seleccione--");
        for (CuentaDto cuenta:cuentaDtos){
            cbBancos.add(cuenta.nombreBanco());
        }
        cbBanco.setItems(cbBancos);
        cbBanco.getSelectionModel().select("--Seleccione--");
    }
    private void  initComboTipo(){
        ObservableList<String> cbTipos = FXCollections.observableArrayList();
        cbTipos.addAll("--Seleccione--");
        for (CuentaDto cuenta:cuentaDtos){
            cbTipos.add(cuenta.tipoCuenta());
        }
        cbTipoCuenta.setItems(cbTipos);
        cbTipoCuenta.getSelectionModel().select("--Seleccione--");
    }
}


