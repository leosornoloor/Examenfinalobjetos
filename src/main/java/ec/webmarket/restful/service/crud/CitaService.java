package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.persistence.CitaRepository;
import ec.webmarket.restful.persistence.HorarioRepository;
import ec.webmarket.restful.persistence.OdontologoRepository;
import ec.webmarket.restful.persistence.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final HorarioRepository horarioRepository;

    public CitaService(CitaRepository citaRepository, PacienteRepository pacienteRepository,
                       OdontologoRepository odontologoRepository, HorarioRepository horarioRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.horarioRepository = horarioRepository;
    }

    public CitaDTO create(CitaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
        
        Odontologo odontologo = odontologoRepository.findById(dto.getOdontologoId())
                .orElseThrow(() -> new IllegalArgumentException("Odontólogo no encontrado"));
        
        Horario horario = horarioRepository.findById(dto.getHorarioId())
                .orElseThrow(() -> new IllegalArgumentException("Horario no encontrado"));

        if (!horario.isDisponible()) {
            throw new IllegalArgumentException("El horario seleccionado no está disponible.");
        }

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setOdontologo(odontologo);
        cita.setHorario(horario);
        cita.setFechaHora(dto.getFechaHora());
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setEstado(Cita.EstadoCita.valueOf(dto.getEstado()));

        // Marcar el horario como no disponible
        horario.setDisponible(false);
        horarioRepository.save(horario);

        return toDTO(citaRepository.save(cita));
    }

    public CitaDTO update(Long id, CitaDTO dto) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con ID: " + id));

        cita.setFechaHora(dto.getFechaHora());
        cita.setMotivoConsulta(dto.getMotivoConsulta());

        try {
            cita.setEstado(Cita.EstadoCita.valueOf(dto.getEstado()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido: " + dto.getEstado());
        }

        return toDTO(citaRepository.save(cita));
    }

    public boolean cancel(Long id) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado(Cita.EstadoCita.CANCELADA);
            cita.getHorario().setDisponible(true); // Liberar el horario
            horarioRepository.save(cita.getHorario());
            citaRepository.save(cita);
            return true;
        }).orElse(false);
    }

    public List<CitaDTO> findAll() {
        return citaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CitaDTO> findByPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CitaDTO> findByOdontologo(Long odontologoId) {
        return citaRepository.findByOdontologoId(odontologoId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    private CitaDTO toDTO(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setPacienteId(cita.getPaciente().getId());
        dto.setOdontologoId(cita.getOdontologo().getId());
        dto.setHorarioId(cita.getHorario().getId());
        dto.setFechaHora(cita.getFechaHora());
        dto.setMotivoConsulta(cita.getMotivoConsulta());
        dto.setEstado(cita.getEstado().name());
        return dto;
    }
}
