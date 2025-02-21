package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.persistence.HorarioRepository;
import ec.webmarket.restful.persistence.OdontologoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private final OdontologoRepository odontologoRepository;

    public HorarioService(HorarioRepository horarioRepository, OdontologoRepository odontologoRepository) {
        this.horarioRepository = horarioRepository;
        this.odontologoRepository = odontologoRepository;
    }

    public HorarioDTO crearHorario(HorarioDTO dto) {
        Horario horario = toEntity(dto);

        if (dto.getOdontologoId() != null) {
            Optional<Odontologo> odontologoOpt = odontologoRepository.findById(dto.getOdontologoId());
            if (odontologoOpt.isPresent()) {
                horario.setOdontologo(odontologoOpt.get());
            } else {
                throw new IllegalArgumentException("El odontólogo con ID " + dto.getOdontologoId() + " no existe.");
            }
        } else {
            throw new IllegalArgumentException("El ID del odontólogo es obligatorio.");
        }

        Horario savedHorario = horarioRepository.save(horario);
        return toDTO(savedHorario);
    }

    public HorarioDTO actualizarHorario(Long id, HorarioDTO dto) {
        Optional<Horario> optionalHorario = horarioRepository.findById(id);
        if (optionalHorario.isEmpty()) {
            throw new IllegalArgumentException("No se encontró un horario con el ID proporcionado.");
        }

        Horario horario = optionalHorario.get();
        horario.setInicio(dto.getInicio());
        horario.setFin(dto.getFin());
        horario.setDisponible(dto.isDisponible());

        Horario updatedHorario = horarioRepository.save(horario);
        return toDTO(updatedHorario);
    }

    public List<HorarioDTO> obtenerHorariosPorOdontologo(Long odontologoId) {
        List<Horario> horarios = horarioRepository.findByOdontologoId(odontologoId);
        return horarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<HorarioDTO> obtenerHorariosPorFecha(LocalDate fecha) {
        List<Horario> horarios = horarioRepository.findByInicioBetween(
            fecha.atStartOfDay(), // 00:00:00
            fecha.atTime(23, 59, 59) // 23:59:59
        );
        return horarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<HorarioDTO> obtenerHorariosDisponibles(boolean disponible) {
        List<Horario> horarios = horarioRepository.findByDisponible(disponible);
        return horarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    public List<HorarioDTO> obtenerTodosLosHorarios() {
        List<Horario> horarios = horarioRepository.findAll();
        return horarios.stream().map(this::toDTO).collect(Collectors.toList());
    }


    private HorarioDTO toDTO(Horario horario) {
        HorarioDTO dto = new HorarioDTO();
        dto.setId(horario.getId());
        dto.setInicio(horario.getInicio());
        dto.setFin(horario.getFin());
        dto.setDisponible(horario.isDisponible());
        if (horario.getOdontologo() != null) {
            dto.setOdontologoId(horario.getOdontologo().getId());
        }
        return dto;
    }

    private Horario toEntity(HorarioDTO dto) {
        Horario horario = new Horario();
        horario.setId(dto.getId());
        horario.setInicio(dto.getInicio());
        horario.setFin(dto.getFin());
        horario.setDisponible(dto.isDisponible());
        return horario;
    }
}
