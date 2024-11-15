package co.edu.unquindio.proyectofinal.dwr.model;

import co.edu.unquindio.proyectofinal.dwr.exceptions.UsuarioExceptions;
import co.edu.unquindio.proyectofinal.dwr.model.Service.IPesService;

import java.io.Serializable;
import java.util.ArrayList;

public class Pes implements IPesService, Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    ArrayList<Presupuesto> listaPresupuesto= new ArrayList<>();
    ArrayList<Categoria> listaCategorias= new ArrayList<>();

    ArrayList<Cuenta> listaCuentas= new ArrayList<>();

    ArrayList<Transaccion> listaTransacciones= new ArrayList<>();

    public Pes() {
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Presupuesto> getListaPresupuesto() {
        return listaPresupuesto;
    }

    public void setListaPresupuesto(ArrayList<Presupuesto> listaPresupuesto) {
        this.listaPresupuesto = listaPresupuesto;
    }

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioExceptions {
        getListaUsuarios().add(nuevoUsuario);
    }

    @Override
    public Usuario crearUsuario(String idUsuario, int saldoTotal, String nombre, String documento, String telefono, String correo, String edad, String direccion) throws UsuarioExceptions {
        Usuario nuevoUsuario = null;
        boolean usuarioExiste = verificarUsuarioExistente(idUsuario);
        if(usuarioExiste){
            throw new UsuarioExceptions("El usuario con id de usuario: "+ idUsuario +" ya existe");
        }else{
            nuevoUsuario = new Usuario();
            nuevoUsuario.setIdUsuario((idUsuario));
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEdad(edad);
            nuevoUsuario.setDocumento(documento);
            nuevoUsuario.setDireccion(direccion);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setSaldoTotal(saldoTotal);

            getListaUsuarios().add(nuevoUsuario);
        }
        return nuevoUsuario;
    }

    @Override
    public Boolean eliminarUsuario(String idUsuario) throws UsuarioExceptions {
        Usuario usuario = null;
        boolean flagExiste = false;
        usuario = obtenerUsuario(idUsuario);
        if(usuario == null)
            throw new UsuarioExceptions("El usuario a eliminar no existe");
        else{
            getListaUsuarios().remove(usuario);
            flagExiste = true;
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarUsuario(String idUsuarioActual, Usuario usuario) throws UsuarioExceptions {
        Usuario usuarioActual = obtenerUsuario(idUsuarioActual);
        if(usuarioActual == null)
            throw new UsuarioExceptions("El usuario a actualizar no existe");
        else{
            usuarioActual.setIdUsuario(usuario.getIdUsuario());
            usuarioActual.setCorreo(usuario.getCorreo());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setEdad(usuario.getEdad());
            usuarioActual.setDocumento(usuario.getDocumento());
            usuarioActual.setDireccion(usuario.getDireccion());
            usuarioActual.setTelefono(usuario.getTelefono());
            usuarioActual.setSaldoTotal(usuario.getSaldoTotal());
            return true;
        }
    }
    @Override
    public boolean verificarUsuarioExistente(String idUsuario) throws UsuarioExceptions {
        if(usuarioExiste(idUsuario)){
            throw new UsuarioExceptions("El usuario con id de usuario: "+idUsuario+" ya existe");
        }else{
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String idUsuario) {
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : getListaUsuarios()) {
            if(usuario.getIdUsuario().equalsIgnoreCase(idUsuario)){
                usuarioEncontrado = usuario;
                break;
            }
        }
        return usuarioEncontrado;
    }

    @Override
    public ArrayList<Usuario> obtenerUsuarios() {
        // TODO Auto-generated method stub
        return getListaUsuarios();
    }

    public boolean usuarioExiste(String idUsuario) {
        boolean usuarioEncontrado = false;
        for (Usuario usuario : getListaUsuarios()) {
            if(usuario.getIdUsuario().equalsIgnoreCase((idUsuario))){
                usuarioEncontrado = true;
                break;
            }
        }
        return usuarioEncontrado;
    }

}
