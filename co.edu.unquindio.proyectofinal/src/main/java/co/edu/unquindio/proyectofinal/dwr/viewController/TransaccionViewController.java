package co.edu.unquindio.proyectofinal.dwr.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;




public class TransaccionViewController {





        @FXML
        private TextField txtMonto;

        @FXML
        private TableColumn<?, ?> colFecha;

        @FXML
        private TableColumn<?, ?> colCuentaDestino;

        @FXML
        private TableColumn<?, ?> colMonto;

        @FXML
        private Button btnFiltrarTransacciones;

        @FXML
        private ComboBox<?> cbTipoTransaccion;

        @FXML
        private TextField txtCuentaDestino;

        @FXML
        private DatePicker datePicker;

        @FXML
        private TableColumn<?, ?> colCuentaOrigen;

        @FXML
        private Button btnListarTransacciones;

        @FXML
        private TextField txtCuentaOrigen;

        @FXML
        private TextField txtIdTransaccion;

        @FXML
        private TableColumn<?, ?> colDescripcion;

        @FXML
        private TableColumn<?, ?> colIdTransaccion;

        @FXML
        private TableColumn<?, ?> colTipoTransaccion;

        @FXML
        private TableView<?> tableTransacciones;

        @FXML
        private Button btnCrearTransaccion;

        @FXML
        private TextArea txtDescripcion;

    }


