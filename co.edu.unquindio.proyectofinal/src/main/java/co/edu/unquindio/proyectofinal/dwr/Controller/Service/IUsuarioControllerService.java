package co.edu.unquindio.proyectofinal.dwr.Controller.Service;

import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;

import java.util.List;

public interface IUsuarioControllerService {
    List<UsuarioDto> obtenerUsuarios();

    boolean agregarUsuario(UsuarioDto usuarioDto);

    boolean eliminarUsuario(String idUsuario);

    boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto);

    void registrarAcciones(String mensaje, int nivel, String accion);
}
