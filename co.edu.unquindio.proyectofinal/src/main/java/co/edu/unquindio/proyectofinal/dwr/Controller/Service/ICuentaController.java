package co.edu.unquindio.proyectofinal.dwr.Controller.Service;

import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;

import java.util.List;

public interface ICuentaController {
    List<CuentaDto> obtenerCuentas();

    boolean agregarCuenta(CuentaDto cuentaDto);

    boolean eliminarCuenta(String idCuenta);

    boolean actualizarUsuario(String idCuentaActual,CuentaDto cuentaDto);

    boolean actualizarCuenta(String idCuentaActual, CuentaDto cuentaDto);

    void registrarAcciones(String mensaje, int nivel, String accion);
}
