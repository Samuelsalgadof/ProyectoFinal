package co.edu.unquindio.proyectofinal.dwr.viewController;


import co.edu.unquindio.proyectofinal.dwr.Controller.UsuarioController;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class UsuarioViewController {

    UsuarioController usuarioControllerService;
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML
    private Button btnEliminarUsuario;

    @FXML
    private Button btnNuevoUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcNombre;

    @FXML
    private TableColumn<UsuarioDto, String> tcTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtidUsuario;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TableView<UsuarioDto> tablaUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcCorreo;

    @FXML
    private TableColumn<UsuarioDto, String> tcDocumento;

    @FXML
    private Button btnActualizarUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcSaldoTotal;

    @FXML
    private TableColumn<UsuarioDto, String> tcDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TableColumn<UsuarioDto, String> tcEdad;

    @FXML
    private Button btnAgregarUsuario;

    @FXML
    private TableColumn<UsuarioDto, String>tcidUsuario;

    @FXML
    private TextField txtSaldoTotal;

    @FXML
    void onAgregarUsuario(ActionEvent event) {
        agregarUsuario();
    }

    private void agregarUsuario() {

        UsuarioDto usuarioDto = construirUsuarioDto();

        if(datosValidos(usuarioDto)){
            if(usuarioControllerService.agregarUsuario(usuarioDto)){
                listaUsuariosDto.add(usuarioDto);
                mostrarMensaje("Notificación Usuario", "Usuario creado", "El Usuario se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
                registrarAcciones("Usuario agregado",1,"Agregar Usuario");
            }else{
                mostrarMensaje("Notificación Usuario", "Usuario no creado", "El Usuario no se pudo crear con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Usuario", "Usuario no creado", "Los datos son invalidos", Alert.AlertType.ERROR);
        }
    }


    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
                txtidUsuario.getText(),
                txtCorreo.getText(),
                txtNombre.getText(),
                txtEdad.getText(),
                txtDocumento.getText(),
                txtDireccion.getText(),
                txtTelefono.getText(),
                Integer.parseInt(txtSaldoTotal.getText())

                );
    }

    private void limpiarCamposUsuario() {
        txtidUsuario.setText("");
        txtCorreo.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtDocumento.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtSaldoTotal.setText("");
    }
    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if (usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
        mensaje += "El nombre es invalido \n";
        if (usuarioDto.correo() == null || usuarioDto.correo().equals(""))
        mensaje += "El correo es invalido \n";
        if (usuarioDto.direccion() == null || usuarioDto.direccion().equals(""))
        mensaje += "La direccion es invalida \n";
        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación Usuario", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

        @FXML
        void onEliminarUsuario (ActionEvent event){
            eliminarUsuario();
        }

    private void eliminarUsuario() {
        boolean usuarioEliminado = false;
        if(usuarioSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Seguro que deseas eliminar el usuario?")){
                usuarioEliminado = usuarioControllerService.eliminarUsuario(String.valueOf(usuarioSeleccionado.idUsuario()));
                if(usuarioEliminado == true){
                    listaUsuariosDto.remove(usuarioSeleccionado);
                    usuarioSeleccionado = null;
                    tablaUsuario.getSelectionModel().clearSelection();
                    limpiarCamposUsuario();
                    mostrarMensaje("Notificación Usuario", "Usuario eliminado", "Usuario eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación Usuario", "Usuario no eliminado", "El Usuario no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación Usuario", "Usuario no seleccionado", "Seleccionado un Usuario de la lista", Alert.AlertType.WARNING);
        }
    }

    @FXML
        void onActualizarUsuario (ActionEvent event){
            actualizarUsuario();
        }

    private void actualizarUsuario() {
        boolean usuarioActualizado = false;
        String idUsuarioActual = String.valueOf(usuarioSeleccionado.idUsuario());
        UsuarioDto usuarioDto = construirUsuarioDto();
        if(usuarioSeleccionado != null){
            if(datosValidos(usuarioSeleccionado)){
                usuarioActualizado = usuarioControllerService.actualizarUsuario(idUsuarioActual,usuarioDto);
                if(usuarioActualizado){
                    listaUsuariosDto.remove(usuarioSeleccionado);
                    listaUsuariosDto.add(usuarioDto);
                    tablaUsuario.refresh();
                    mostrarMensaje("Notificación Usuario", "Usuario actualizado", "El Usuario se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposUsuario();
                }else{
                    mostrarMensaje("Notificación Usuario", "Usuario no actualizado", "El Usuario no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación Usuario", "Usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    @FXML
        void onNuevoUsuario (ActionEvent event){
            txtidUsuario.setText("Ingrese el id usuario");
            txtCorreo.setText("Ingrese el correo del usuario");
            txtNombre.setText("Ingrese el Nombre del usuario");
            txtEdad.setText("Ingrese la edad del usuario");
            txtDocumento.setText("Ingrese el documento del usuario");
            txtDireccion.setText("Ingrese la direccion");
            txtTelefono.setText("Ingrese el telefono del usuario");
            txtSaldoTotal.setText("Ingrese el saldo total");

        }
        @FXML
        void initialize () {
            usuarioControllerService = new UsuarioController();
            intiView();
        }

        private void intiView () {
            initDataBinding();
            obtenerUsuarios();
            tablaUsuario.getItems().clear();
            tablaUsuario.setItems(listaUsuariosDto);
            listenerSelection();
        }
        private void initDataBinding () {
            tcidUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().idUsuario())));
            tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));
            tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
            tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().edad()));
            tcDocumento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().documento()));
            tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
            tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefono()));
            tcSaldoTotal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().saldoTotal())));

        }

        private void obtenerUsuarios () {
            listaUsuariosDto.addAll(usuarioControllerService.obtenerUsuarios());
        }

        private void listenerSelection () {
            tablaUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                usuarioSeleccionado = newSelection;
                mostrarInformacionUsuario(usuarioSeleccionado);
            });
        }
        private void mostrarInformacionUsuario (UsuarioDto usuarioSeleccionado){
            if (usuarioSeleccionado != null) {
                txtidUsuario.setText(String.valueOf(usuarioSeleccionado.idUsuario()));
                txtCorreo.setText(usuarioSeleccionado.correo());
                txtNombre.setText(usuarioSeleccionado.nombre());
                txtEdad.setText(usuarioSeleccionado.edad());
                txtDocumento.setText(usuarioSeleccionado.documento());
                txtDireccion.setText(usuarioSeleccionado.direccion());
                txtTelefono.setText(usuarioSeleccionado.telefono());
                txtSaldoTotal.setText(String.valueOf(usuarioSeleccionado.saldoTotal()));

            }
        }
        private void registrarAcciones(String mensaje, int nivel, String accion) {
            usuarioControllerService.registrarAcciones(mensaje,nivel,accion);

    }


}


