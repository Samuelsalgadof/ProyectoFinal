package co.edu.unquindio.proyectofinal.dwr.mapping.mappers;


import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.CuentaDto;
import co.edu.unquindio.proyectofinal.dwr.mapping.Dto.UsuarioDto;
import co.edu.unquindio.proyectofinal.dwr.model.Cuenta;
import co.edu.unquindio.proyectofinal.dwr.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface PesMapper {

    PesMapper INSTANCE = Mappers.getMapper(PesMapper.class);

    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    @IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);

//    @Named("mappingToEmpeladoDto")
//    EmpleadoDto mappingToEmpeladoDto(Empleado empleado);


    @Mapping(target = "idCuenta", source = "cuenta.nombreBanco")
    @IterableMapping(qualifiedByName = "cunetaToCuentaDto")
    CuentaDto clienteToClienteDto(Cuenta cuenta);

}
