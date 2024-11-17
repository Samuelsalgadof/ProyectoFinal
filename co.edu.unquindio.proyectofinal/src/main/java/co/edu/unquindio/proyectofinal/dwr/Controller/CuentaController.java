package co.edu.unquindio.proyectofinal.dwr.Controller;

import co.edu.unquindio.proyectofinal.dwr.Controller.Service.ICuentaController;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;
import javafx.fxml.FXML;

import java.util.List;

public class CuentaController implements ICuentaController {


    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();


    @FXML
    public void initialize() {
        // Si no se inyectó, se puede instanciar de manera explícita
        if (modelFactoryController == null) {
            modelFactoryController = ModelFactoryController.getInstance();
        }
    }

    @Override
    public List<CuentaDto> obtenerCuentas() {
        return modelFactoryController.obtenerCuentaDTO();
    }

    @Override
    public boolean agregarCuenta(CuentaDto cuentaDto) {
        return modelFactoryController.agregarCuenta(cuentaDto);
    }



    @Override
    public boolean eliminarCuenta(String idCuenta) {
        return modelFactoryController.eliminarCuenta(idCuenta);
    }

    @Override
    public boolean actualizarUsuario(String idCuentaActual, CuentaDto cuentaDto) {
        return false;
    }

    @Override
    public boolean actualizarCuenta(String idCuentaActual, CuentaDto cuentaDto) {
        return modelFactoryController.actualizarCuenta(idCuentaActual, cuentaDto );
    }

    @Override
    public void registrarAcciones(String mensaje, int nivel, String accion) {

    }




}
