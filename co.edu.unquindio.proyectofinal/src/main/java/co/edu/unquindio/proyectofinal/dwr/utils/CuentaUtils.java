package co.edu.unquindio.proyectofinal.dwr.utils;

import co.edu.unquindio.proyectofinal.dwr.model.Cuenta;
import co.edu.unquindio.proyectofinal.dwr.model.Pes;

public class CuentaUtils {

    public static Pes inicializarDatos() {
        Pes pes = new Pes();

        // Crear cuentas
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setIdCuenta(222);
        cuenta1.setSaldo(30000);
        cuenta1.setNombreBanco("BBVA");
        cuenta1.setTipoCuenta("Corriente");
        cuenta1.setNumCuenta(001);

        // Crear datos para el propietario de cuenta1
        cuenta1.setPropietario("Santiago");
        cuenta1.setCorreo("santiago.munozl@uqvirtual.edu.co");
        cuenta1.setTelefono("3147604263");
        cuenta1.setDocumento("1090274004");
        cuenta1.setDireccion("Villa");
        cuenta1.setEdad("22");

        // Agregar cuenta1 a la lista de cuentas
        pes.getListaCuentas().add(cuenta1);


        Cuenta cuenta2 = new Cuenta();
        cuenta2.setIdCuenta(1111);
        cuenta2.setSaldo(20000);
        cuenta2.setNombreBanco("Bancolombia");
        cuenta2.setTipoCuenta("Corriente");
        cuenta2.setNumCuenta(909);

        // Crear datos para el propietario de cuenta2
        cuenta2.setPropietario("Sam");
        cuenta2.setCorreo("samuel.salgadof@uqvirtual.edu.co");
        cuenta2.setTelefono("3002414858");
        cuenta2.setDocumento("1090273435");
        cuenta2.setDireccion("La Adiela");
        cuenta2.setEdad("18");

        // Agregar cuenta2 a la lista de cuentas
        pes.getListaCuentas().add(cuenta2);


        Cuenta cuenta3 = new Cuenta();
        cuenta3.setIdCuenta(444);
        cuenta3.setNombreBanco("BBVA");
        cuenta3.setTipoCuenta("Ahorros");
        cuenta3.setNumCuenta(111);

        // Crear datos para el propietario de cuenta3
        cuenta3.setPropietario("Pablo");
        cuenta3.setCorreo("Pablo.Gonzalesf@uqvirtual.edu.co");
        cuenta3.setTelefono("3002475645");
        cuenta3.setDocumento("109023465");
        cuenta3.setDireccion("Limonar");
        cuenta3.setEdad("18");

        // Agregar cuenta3 a la lista de cuentas
        pes.getListaCuentas().add(cuenta3);

        return pes;
    }

}
