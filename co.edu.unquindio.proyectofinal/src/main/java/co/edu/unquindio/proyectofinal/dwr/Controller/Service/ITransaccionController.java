package co.edu.unquindio.proyectofinal.dwr.Controller.Service;

import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.TransaccionDto;

import java.util.List;

public interface ITransaccionController {
    List<TransaccionDto> obtenerTransaccion();

    boolean agregarTransaccion(TransaccionDto transaccionDto);


    boolean actualizarUsuario(String idTransaccionctual,CuentaDto cuentaDto);

    void registrarAcciones(String mensaje, int nivel, String accion);
}



