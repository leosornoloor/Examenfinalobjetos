package ec.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.webmarket.restful.domain.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
