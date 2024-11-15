package co.edu.unquindio.proyectofinal.dwr.Controller;

import co.edu.unquindio.proyectofinal.dwr.Controller.Service.IModelFactoryControllerService;
import co.edu.unquindio.proyectofinal.dwr.exceptions.UsuarioExceptions;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.mappers.PesMapper;
import co.edu.unquindio.proyectofinal.dwr.model.Pes;
import co.edu.unquindio.proyectofinal.dwr.model.Usuario;
import co.edu.unquindio.proyectofinal.dwr.utils.Persistencia;
import co.edu.unquindio.proyectofinal.dwr.utils.UsuarioUtils;

import java.io.IOException;
import java.util.List;

public class ModelFactoryController implements IModelFactoryControllerService {

    Pes pes;
    PesMapper mapper = PesMapper.INSTANCE;

    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
//         cargarDatosBase();
//     salvarDatosPrueba();

        //2. Cargar los datos de los archivos
//  cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
//     cargarResourceBinario();
//guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
//		guardarResourceXML();
 cargarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null

        if(pes == null){
//            cargarDatosBase();
//           guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }




    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarUsuarios(getPes().getListaUsuarios());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosDesdeArchivos() {
        pes = new Pes();
        try {
            Persistencia.cargarDatosArchivos(pes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void cargarDatosBase() {
        pes = UsuarioUtils.inicializarDatos();
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
        return  mapper.getUsuariosDto(pes.getListaUsuarios());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try{
            if(!pes.verificarUsuarioExistente(String.valueOf((usuarioDto.idUsuario())))) {
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getPes().agregarUsuario(usuario);
                guardarResourceXML();
            }
            return true;
        }catch (UsuarioExceptions e){
            e.getMessage();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;

    }

    @Override
    public boolean actualizarUsuario(String idUsuarioActual, UsuarioDto usuarioDto) {
        try {
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getPes().actualizarUsuario(idUsuarioActual, usuario);
            return true;
        } catch (UsuarioExceptions e) {
            e.printStackTrace();
            return false;
        }
    }
    private void cargarResourceXML() {pes = Persistencia.cargarRecursoPesXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoPesXML(pes);
    }

    private void cargarResourceBinario() {
    pes = Persistencia.   cargarRecursoPesBinario();
    }


    private void guardarResourceBinario() {
        Persistencia.guardarRecursoPesBinario(pes);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
}

