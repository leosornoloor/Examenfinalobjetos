package ec.webmarket.restful.persistence;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ec.webmarket.restful.domain.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByOdontologoId(Long odontologoId);
    List<Horario> findByDisponible(boolean disponible);
    List<Horario> findByInicioBetween(LocalDateTime start, LocalDateTime end);
}
