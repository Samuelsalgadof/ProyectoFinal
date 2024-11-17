package co.edu.unquindio.proyectofinal.dwr.Controller;

import co.edu.unquindio.proyectofinal.dwr.Controller.Service.IModelFactoryControllerService;
import co.edu.unquindio.proyectofinal.dwr.exceptions.CuentaExceptions;
import co.edu.unquindio.proyectofinal.dwr.exceptions.UsuarioExceptions;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.mappers.PesMapper;
import co.edu.unquindio.proyectofinal.dwr.model.Cuenta;
import co.edu.unquindio.proyectofinal.dwr.model.Pes;
import co.edu.unquindio.proyectofinal.dwr.model.Usuario;
import co.edu.unquindio.proyectofinal.dwr.utils.CuentaUtils;
import co.edu.unquindio.proyectofinal.dwr.utils.Persistencia;
import co.edu.unquindio.proyectofinal.dwr.utils.UsuarioUtils;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelFactoryController implements IModelFactoryControllerService {

    private static final Logger logger = Logger.getLogger(ModelFactoryController.class.getName());
    Pes pes;
    PesMapper mapper = PesMapper.INSTANCE;


    //------------------------------  Singleton ------------------------------------------------
    // Clase estática oculta. Se instancia el Singleton una sola vez.
    private static class SingletonHolder {
        private final static ModelFactoryController INSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de la clase.
    public static ModelFactoryController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("Invocación de la clase singleton");
        // Cargar los datos de los archivos al inicio
        cargarResourceXML();

        cargarDatosBase();



        // Siempre verificar si la raíz de 'pes' es null.
        if (pes == null) {
            cargarDatosBase();
            guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }

    private void cargarDatosBase() {
        Pes ayudaPes = CuentaUtils.inicializarDatos();
//        pes = UsuarioUtils.inicializarDatos();//
        pes.setListaCuentas(ayudaPes.getListaCuentas());
    }



    private void cargarResourceXML() {
        pes = Persistencia.cargarRecursoPesXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoPesXML(pes);
    }

    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarUsuarios(getPes().getListaUsuarios());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al guardar los usuarios de prueba: ", e);
        }
    }

    public Pes getPes() {
        return pes;
    }

    public void setPes(Pes pes) {
        this.pes = pes;
    }

    public PesMapper getMapper() {
        return mapper;
    }

    public void setMapper(PesMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(pes.getListaUsuarios());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try {
            if (!pes.verificarUsuarioExistente(String.valueOf(usuarioDto.idUsuario()))) {
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getPes().agregarUsuario(usuario);
                guardarResourceXML();
                return true;
            }
            logger.log(Level.WARNING, "El usuario con ID: " + usuarioDto.idUsuario() + " ya existe.");
            return false;
        } catch (UsuarioExceptions e) {
            logger.log(Level.SEVERE, "Error al agregar el usuario: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean agregarCuenta(CuentaDto cuentaDto) {
        try {
            Cuenta cuenta = mapper.cuentatoCuentaDto(cuentaDto);
            if (!pes.verificarCuentaExistente(cuenta.getIdCuenta())) {
                getPes().agregarCuenta(cuenta);
                guardarResourceXML();
                return true;
            }
            logger.log(Level.WARNING, "La cuenta con ID: " + cuentaDto.idCuenta() + " ya existe.");
            return false;
        } catch (CuentaExceptions e) {
            logger.log(Level.SEVERE, "Error al agregar la cuenta: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String idUsuario) {
        boolean flagExiste = false;
        try {
            flagExiste = getPes().eliminarUsuario(idUsuario);
            guardarResourceXML();
        } catch (UsuarioExceptions e) {
            logger.log(Level.SEVERE, "Error al eliminar el usuario con ID: " + idUsuario, e);
        }
        return flagExiste;
    }

    @Override
    public boolean eliminarCuenta(String idCuenta) {
        boolean flagExiste = false;
        try {
            flagExiste = getPes().eliminarCuenta(idCuenta);
            guardarResourceXML();
        } catch (CuentaExceptions e) {
            logger.log(Level.SEVERE, "Error al eliminar la cuenta con ID: " + idCuenta, e);
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto) {
        try {
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getPes().actualizarUsuario(idUsuarioActual, usuario);
            guardarResourceXML();
            return true;
        } catch (UsuarioExceptions e) {
            logger.log(Level.SEVERE, "Error al actualizar el usuario con ID: " + idUsuarioActual, e);
            return false;
        }
    }

    @Override
    public boolean actualizarCuenta(String idCuentaActual, CuentaDto cuentaDto) {
        try {
            Cuenta cuenta = mapper.cuentatoCuentaDto(cuentaDto);
            getPes().actualizarCuenta(idCuentaActual, cuenta);
            guardarResourceXML();
            return true;
        } catch (CuentaExceptions e) {
            logger.log(Level.SEVERE, "Error al actualizar la cuenta con ID: " + idCuentaActual, e);
            return false;
        }
    }

    public boolean iniciarSesion(String user, String password) {
        return Persistencia.iniciarSesion(user, password);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
    public List<CuentaDto> obtenerCuentaDTO() {
        System.out.println(pes.getListaCuentas());
        return mapper.getCuentaDto(pes.getListaCuentas());
    }
}


