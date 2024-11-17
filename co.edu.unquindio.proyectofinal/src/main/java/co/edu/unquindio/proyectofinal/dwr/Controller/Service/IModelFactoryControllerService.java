package co.edu.unquindio.proyectofinal.dwr.Controller.Service;

import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;

import java.util.List;

public interface IModelFactoryControllerService {

    List<UsuarioDto> obtenerUsuarios();
    boolean agregarUsuario(UsuarioDto usuarioDto);

    boolean agregarCuenta(CuentaDto cuentaDto);

    boolean eliminarUsuario(String idUsuario);

    boolean eliminarCuenta(String idCuenta);

    boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto);

    boolean actualizarCuenta(String idCuentaActual, CuentaDto cuentaDto);



}
