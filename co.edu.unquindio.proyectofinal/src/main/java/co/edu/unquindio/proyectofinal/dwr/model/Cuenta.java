package co.edu.unquindio.proyectofinal.dwr.model;

import java.io.Serializable;

public class Cuenta extends Usuario implements Serializable {
    private static int contador = 0;
    private int idCuenta;
    private String nombreBanco;
    private int numCuenta;
    private String tipoCuenta;

    public Cuenta() {
        this.idCuenta = contador++;
    }

    public Cuenta(String nombreBanco, int numCuenta, String tipoCuenta) {
        this.idCuenta = contador++;
        this.nombreBanco = nombreBanco;
        this.numCuenta = numCuenta;
        this.tipoCuenta = tipoCuenta;

    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setSaldo(int i) {
    }
}
