package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.dto.v1.UsuarioDTO;
import ec.webmarket.restful.persistence.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO registrarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.findByNombreUsuario(dto.getNombreUsuario()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }

        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public UsuarioDTO autenticarUsuario(String nombreUsuario, String clave) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuarioOpt.isPresent() && usuarioOpt.get().getClave().equals(clave)) {
            return modelMapper.map(usuarioOpt.get(), UsuarioDTO.class);
        }
        throw new IllegalArgumentException("Credenciales inválidas.");
    }

    public void actualizarClave(Long id, String nuevaClave) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.setClave(nuevaClave);
        usuarioRepository.save(usuario);
    }
}
