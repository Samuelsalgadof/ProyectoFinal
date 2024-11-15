package co.edu.unquindio.proyectofinal.dwr.utils;

import co.edu.unquindio.proyectofinal.dwr.model.Pes;
import co.edu.unquindio.proyectofinal.dwr.model.Usuario;

public class UsuarioUtils {

    public static Pes inicializarDatos() {
        Pes pes = new Pes();

        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario("111");
        usuario1.setCorreo("santiago.munozl@uqvirtual.edu.co");
        usuario1.setNombre("Santiago");
        usuario1.setEdad("22");
        usuario1.setDocumento("1090274004");
        usuario1.setDireccion("villa");
        usuario1.setTelefono("3147604263");
        usuario1.setSaldoTotal(50000);
        pes.getListaUsuarios().add(usuario1);


        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario("222");
        usuario2.setCorreo("samuel.salgadof@uqvirtual.edu.co");
        usuario2.setNombre("Sam");
        usuario2.setEdad("18");
        usuario2.setDocumento("1090273435");
        usuario2.setDireccion("La Adiela");
        usuario2.setTelefono("3002414858");
        usuario2.setSaldoTotal(90000);
        pes.getListaUsuarios().add(usuario2);


        Usuario usuario3 = new Usuario();
        usuario3.setIdUsuario("333");
        usuario3.setCorreo("Pablo.Gonzalesf@uqvirtual.edu.co");
        usuario3.setNombre("Pablo");
        usuario3.setEdad("18");
        usuario3.setDocumento("109023465");
        usuario3.setDireccion("Limonar");
        usuario3.setTelefono("3002475645");
        usuario2.setSaldoTotal(80000);
        pes.getListaUsuarios().add(usuario3);

        return pes;

    }
}
