package ec.webmarket.restful.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.webmarket.restful.domain.Odontologo;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    List<Odontologo> findByEspecialidad(String especialidad);
}