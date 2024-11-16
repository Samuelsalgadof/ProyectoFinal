package co.edu.unquindio.proyectofinal.dwr.utils;

//import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
//import co.edu.uniquindio.banco.bancouq.model.*;
import co.edu.unquindio.proyectofinal.dwr.model.Pes;
import co.edu.unquindio.proyectofinal.dwr.model.Usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Persistencia {



    //bancoUq/src/main/resources/persistencia/archivoClientes.txt|
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivoUsuarios.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/Log/UsuariosLog.txt";
    public static final String RUTA_ARCHIVO_MODELO_PES_BINARIO = "src/main/resources/persistencia/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_PES_XML = "src/main/resources/persistencia/model.xml";

//	C:\td\persistencia



    public static void cargarDatosArchivos(Pes pes) throws FileNotFoundException, IOException {
        //cargar archivo de clientes
        ArrayList<Usuario> usuariosCargados = cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
        if(usuariosCargados.size() > 0)
           pes.getListaUsuarios().addAll(usuariosCargados);

//
//        //cargar archivo transcciones
//
//        //cargar archivo empleados
//
//        //cargar archivo prestamo
//
   }

//    /**
//     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
//     * @param
//     * @param
//     * @throws IOException
//     */
//    public static void guardarClientes(ArrayList<Cliente> listaClientes) throws IOException {
//        // TODO Auto-generated method stub
//        String contenido = "";
//        for(Cliente cliente:listaClientes)
//        {
//            contenido+= cliente.getNombre()+","+cliente.getApellido()+","+cliente.getCedula()+","+cliente.getDireccion()
//                    +","+cliente.getCorreo()+","+cliente.getFechaNacimiento()+","+cliente.getTelefono()+"\n";
//        }
//        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, false);
//    }


    public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
        String contenido = "";
        for(Usuario usuario:listaUsuarios)
        {
            contenido+= usuario.getNombre()+
                    ","+usuario.getDocumento()+
                    ","+usuario.getTelefono()+
                    ","+usuario.getCorreo()+
                    ","+usuario.getEdad()+
                    ","+usuario.getIdUsuario()+
                    ","+usuario.getSaldoTotal()+
                    ","+usuario.getDireccion()+"\n";

        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
    }



//	----------------------LOADS------------------------

//    /**
//     *
//     * @param
//     * @param
//     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
//     * @throws FileNotFoundException
//     * @throws IOException
//     */
//    public static ArrayList<Cliente> cargarClientes() throws FileNotFoundException, IOException
//    {
//        ArrayList<Cliente> clientes =new ArrayList<Cliente>();
//        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
//        String linea="";
//        for (int i = 0; i < contenido.size(); i++)
//        {
//            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
//            Cliente cliente = new Cliente();
//            cliente.setNombre(linea.split(",")[0]);
//            cliente.setApellido(linea.split(",")[1]);
//            cliente.setCedula(linea.split(",")[2]);
//            cliente.setDireccion(linea.split(",")[3]);
//            cliente.setCorreo(linea.split(",")[4]);
//            cliente.setFechaNacimiento(linea.split(",")[5]);
//            cliente.setTelefono(linea.split(",")[6]);
//            clientes.add(cliente);
//        }
//        return clientes;
//    }


//    public static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException {
//        ArrayList<Empleado> empleados =new ArrayList<Empleado>();
//        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
//        String linea="";
//        for (int i = 0; i < contenido.size(); i++)
//        {
//            linea = contenido.get(i);
//            Empleado empleado = new Empleado();
//            empleado.setNombre(linea.split(",")[0]);
//            empleado.setApellido(linea.split(",")[1]);
//            empleado.setCedula(linea.split(",")[2]);
//            empleado.setFechaNacimiento(linea.split(",")[3]);
//            empleados.add(empleado);
//        }
//        return empleados;
//    }


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }


//    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExcepcion {
//
//        if(validarUsuario(usuario,contrasenia)) {
//            return true;
//        }else {
//            throw new UsuarioExcepcion("Usuario no existe");
//        }
//
//    }

    private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);

        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
        {
            Usuario usuarioAux = usuarios.get(indiceUsuario);
            if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return true;
            }
        }
        return false;
    }


    public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";

        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);

            Usuario usuario = new Usuario();
            usuario.setNombre(linea.split(",")[0]);
            usuario.setDocumento(linea.split(",")[1]);
            usuario.setTelefono(linea.split(",")[2]);
            usuario.setCorreo(linea.split(",")[3]);
            usuario.setEdad(linea.split(",")[4]);
            usuario.setIdUsuario(linea.split(",")[5]);
            int saldoTotal = Integer.parseInt(linea.split(",")[6]);
            usuario.setSaldoTotal(saldoTotal);
            usuario.setDireccion(linea.split(",")[7]);




            usuarios.add(usuario);
        }
        return usuarios;
    }


//	----------------------SAVES------------------------

//    /**
//     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
//     * @param
//     * @param ruta
//     * @throws IOException
//     */
//
//    public static void guardarObjetos(ArrayList<Cliente> listaClientes, String ruta) throws IOException  {
//        String contenido = "";
//
//        for(Cliente clienteAux:listaClientes) {
//            contenido+= clienteAux.getNombre()+","+clienteAux.getApellido()+","+clienteAux.getCedula()+clienteAux.getDireccion()
//                    +","+clienteAux.getCorreo()+","+clienteAux.getFechaNacimiento()+","+clienteAux.getTelefono()+"\n";
//        }
//        ArchivoUtil.guardarArchivo(ruta, contenido, true);
//    }





//    ------------------------------------SERIALIZACIÓN  y XML

    public static Pes cargarRecursoPesBinario() {

        Pes pes = null;

        try {
            pes = (Pes) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_PES_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pes;
    }
    public static void guardarRecursoPesBinario(Pes pes) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_PES_BINARIO, pes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//    public static Pes cargarRecursoPesXML() {
//
//        Pes pes = null;
//
//        try {
//            Pes = (pes)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PES_BINARIO);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return pes;
//
//    }
//
//
//

    public static Pes cargarRecursoPesXML() {

        Pes pes= null;

        try {
            pes = (Pes) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PES_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pes;

    }
    public static void guardarRecursoPesXML(Pes pes) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PES_XML, pes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean iniciarSesion(String user, String password){
        return ArchivoUtil.iniciarSesion(user,password,RUTA_ARCHIVO_USUARIOS);
    }
}









