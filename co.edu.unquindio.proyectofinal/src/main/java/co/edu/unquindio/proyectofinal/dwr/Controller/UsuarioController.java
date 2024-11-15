package co.edu.unquindio.proyectofinal.dwr.Controller;

import co.edu.unquindio.proyectofinal.dwr.Controller.Service.IUsuarioControllerService;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;

import java.util.List;

public class UsuarioController implements IUsuarioControllerService {

    ModelFactoryController modelFactoryController;

    public UsuarioController() {
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<UsuarioDto> obtenerUsuarios() {
        return modelFactoryController.obtenerUsuarios();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    @Override
    public boolean eliminarUsuario(String idUsuario) {
        return modelFactoryController.eliminarUsuario(idUsuario);
    }

    @Override
    public boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto) {
        return modelFactoryController.actualizarUsuario(idUsuarioActual, usuarioDto);
    }

    @Override
    public void registrarAcciones(String mensaje, int nivel, String accion) {
        modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
    }


}