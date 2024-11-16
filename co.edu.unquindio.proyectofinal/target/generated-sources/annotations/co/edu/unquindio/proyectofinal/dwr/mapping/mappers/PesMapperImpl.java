package co.edu.unquindio.proyectofinal.dwr.mapping.mappers;

import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;
import co.edu.unquindio.proyectofinal.dwr.model.Cuenta;
import co.edu.unquindio.proyectofinal.dwr.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-15T17:15:34-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
*/
public class PesMapperImpl implements PesMapper {

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String idUsuario = null;
        String correo = null;
        String nombre = null;
        String edad = null;
        String documento = null;
        String direccion = null;
        String telefono = null;
        int saldoTotal = 0;

        idUsuario = usuario.getIdUsuario();
        correo = usuario.getCorreo();
        nombre = usuario.getNombre();
        edad = usuario.getEdad();
        documento = usuario.getDocumento();
        direccion = usuario.getDireccion();
        telefono = usuario.getTelefono();
        saldoTotal = usuario.getSaldoTotal();

        UsuarioDto usuarioDto = new UsuarioDto( idUsuario, correo, nombre, edad, documento, direccion, telefono, saldoTotal );

        return usuarioDto;
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNombre( usuarioDto.nombre() );
        usuario.setDocumento( usuarioDto.documento() );
        usuario.setTelefono( usuarioDto.telefono() );
        usuario.setCorreo( usuarioDto.correo() );
        usuario.setEdad( usuarioDto.edad() );
        usuario.setDireccion( usuarioDto.direccion() );
        usuario.setIdUsuario( usuarioDto.idUsuario() );
        usuario.setSaldoTotal( usuarioDto.saldoTotal() );

        return usuario;
    }

    @Override
    public List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios) {
        if ( listaUsuarios == null ) {
            return null;
        }

        List<UsuarioDto> list = new ArrayList<UsuarioDto>( listaUsuarios.size() );
        for ( Usuario usuario : listaUsuarios ) {
            list.add( usuarioToUsuarioDto( usuario ) );
        }

        return list;
    }

    @Override
    public CuentaDto clienteToClienteDto(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }

        String idCuenta = null;
        String nombreBanco = null;
        int numCuenta = 0;
        String tipoCuenta = null;

        idCuenta = cuenta.getNombreBanco();
        nombreBanco = cuenta.getNombreBanco();
        numCuenta = cuenta.getNumCuenta();
        tipoCuenta = cuenta.getTipoCuenta();

        CuentaDto cuentaDto = new CuentaDto( idCuenta, nombreBanco, numCuenta, tipoCuenta );

        return cuentaDto;
    }
}
