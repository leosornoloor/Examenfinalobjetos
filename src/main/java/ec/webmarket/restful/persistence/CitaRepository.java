package ec.webmarket.restful.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ec.webmarket.restful.domain.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPacienteId(Long pacienteId);
    List<Cita> findByOdontologoId(Long odontologoId);
}
