package co.edu.unquindio.proyectofinal.dwr.model.Service;

import co.edu.unquindio.proyectofinal.dwr.model.Cuenta;
import co.edu.unquindio.proyectofinal.dwr.model.Usuario;
import co.edu.unquindio.proyectofinal.dwr.exceptions.UsuarioExceptions;

import java.util.ArrayList;

public interface IPesService {
    public Usuario crearUsuario(String idUsuario,int saldoTotal,String nombre, String documento, String telefono, String correo, String edad, String direccion) throws UsuarioExceptions;
    public Boolean eliminarUsuario(String idUsuario)throws UsuarioExceptions;

    Boolean eliminarCuenta(String idCuenta) throws UsuarioExceptions;

    boolean actualizarUsuario(String idUsuarioActual, Usuario usuario) throws UsuarioExceptions;
    public boolean  verificarUsuarioExistente(String idUsuario) throws UsuarioExceptions;
    public Usuario obtenerUsuario(String idUsuario);

    Cuenta obtenerCuenta(String idCuenta);

    public ArrayList<Usuario> obtenerUsuarios();
}
