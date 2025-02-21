package ec.webmarket.restful.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.webmarket.restful.domain.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByPacienteId(Long pacienteId);
}
