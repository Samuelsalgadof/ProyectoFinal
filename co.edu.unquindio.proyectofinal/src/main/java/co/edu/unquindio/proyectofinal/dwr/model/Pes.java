package co.edu.unquindio.proyectofinal.dwr.model;

import co.edu.unquindio.proyectofinal.dwr.exceptions.CuentaExceptions;
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

    public void agregarCuenta(Cuenta nuevaCuenta) throws UsuarioExceptions {
        getListaCuentas().add(nuevaCuenta);
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

    public Cuenta crearCuenta(String idCuenta, int saldoTotal, String nombreBanco, String tipoCuenta, String numeroCuenta) throws CuentaExceptions {
        Cuenta nuevaCuenta = null;
        boolean cuentaExiste = verificarCuentaExistente(Integer.parseInt(idCuenta));
        if (cuentaExiste) {
            throw new CuentaExceptions("La cuenta con id de cuenta: " + idCuenta + " ya existe");
        } else {
            nuevaCuenta = new Cuenta();
            nuevaCuenta.setIdCuenta(Integer.parseInt(idCuenta));
            nuevaCuenta.setNombreBanco(nombreBanco);
            nuevaCuenta.setTipoCuenta(tipoCuenta);
            nuevaCuenta.setNumCuenta(Integer.parseInt(numeroCuenta));
            nuevaCuenta.setSaldoTotal(saldoTotal);

            getListaCuentas().add(nuevaCuenta);
        }
        return nuevaCuenta;
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
    public Boolean eliminarCuenta(String idCuenta) throws UsuarioExceptions {
        Cuenta cuenta = null;
        boolean flagExiste = false;
       cuenta = obtenerCuenta(idCuenta);
        if(cuenta == null)
            throw new CuentaExceptions("La Cuenta a eliminar no existe");
        else{
            getListaCuentas().remove(cuenta);
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

    public boolean actualizarCuenta(String idCuentaActual, Cuenta cuenta) throws CuentaExceptions {

        Cuenta cuentaActual = obtenerCuenta(idCuentaActual);

        if (cuentaActual == null) {

            throw new CuentaExceptions("La cuenta a actualizar no existe");
        } else {

            cuentaActual.setIdCuenta(cuenta.getIdCuenta());
            cuentaActual.setNombreBanco(cuenta.getNombreBanco());
            cuentaActual.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaActual.setNumCuenta(cuenta.getNumCuenta());

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

    public boolean verificarCuentaExistente(int idCuenta) throws CuentaExceptions {
        if (cuentaExiste(idCuenta)) {
            // Lanzamos una excepción si la cuenta ya existe
            throw new CuentaExceptions("La cuenta con id de cuenta: " + idCuenta + " ya existe");
        }
        // Retornamos true si la cuenta no existe
        return true;
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
    public Cuenta obtenerCuenta(String idCuenta) {
        if (idCuenta == null || idCuenta.trim().isEmpty()) {
            System.out.println("El idCuenta es nulo o está vacío.");
            return null;
        }

        int idCuentaInt;
        try {
            idCuentaInt = Integer.parseInt(idCuenta.trim());
        } catch (NumberFormatException e) {
            System.out.println("El idCuenta no es un número válido.");
            return null;
        }

        for (Cuenta cuenta : getListaCuentas()) {
            System.out.println("Comparando '" + cuenta.getIdCuenta() + "' con '" + idCuentaInt + "'");
            if (cuenta.getIdCuenta() == idCuentaInt) {
                System.out.println("Cuenta encontrada: " + cuenta);
                return cuenta;
            }
        }
        System.out.println("Cuenta no encontrada.");
        return null;
    }


    @Override
    public ArrayList<Usuario> obtenerUsuarios() {
        // TODO Auto-generated method stub
        return getListaUsuarios();
    }

    public ArrayList<Cuenta> obtenerCuentas() {
        // TODO Auto-generated method stub
        return getListaCuentas();
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

    public boolean cuentaExiste(int idCuenta) {
        boolean cuentaEncontrada = false;


        for (Cuenta cuenta : getListaCuentas()) {

            if (cuenta.getIdCuenta() == idCuenta) {
                cuentaEncontrada = true;
                break;
            }
        }
        return cuentaEncontrada;
    }







}
