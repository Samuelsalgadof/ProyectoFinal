package co.edu.unquindio.proyectofinal.dwr.model;

import java.io.Serializable;

public class Transaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idTransaccion;
    private String fecha;
    private String tipoTransaccion;
    private String monto;
    private String Descripcion;
    private String cuentaOrigen;
    private String cuentaDestino;

    public Transaccion(String idTransaccion, String cuentaDestino, String cuentaOrigen, String descripcion, String monto, String tipoTransaccion, String fecha) {
        this.idTransaccion = idTransaccion;
        this.cuentaDestino = cuentaDestino;
        this.cuentaOrigen = cuentaOrigen;
        Descripcion = descripcion;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
