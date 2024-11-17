package co.edu.unquindio.proyectofinal.dwr.mapping.Dto;

public record TransaccionDto(String idTransaccion, String cuentaDestino, String cuentaOrigen, String descripcion, String monto, String tipoTransaccion, String fecha) {
}
