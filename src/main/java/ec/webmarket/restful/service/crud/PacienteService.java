package ec.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.persistence.PacienteRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class PacienteService extends GenericCrudServiceImpl<Paciente, PacienteDTO> {

    @Autowired
    private PacienteRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Paciente> find(PacienteDTO dto) {
        return repository.findById(dto.getId());
    }

    public List<Paciente> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public Optional<Paciente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(PacienteDTO dto) {
        repository.deleteById(dto.getId());
    }
    
    @Override
    public Paciente mapToDomain(PacienteDTO dto) {
        return modelMapper.map(dto, Paciente.class);
    }

    @Override
    public PacienteDTO mapToDto(Paciente domain) {
        return modelMapper.map(domain, PacienteDTO.class);
    }

    public Optional<Paciente> findByNumeroCedula(Long numeroCedula) {
        return repository.findByNumeroCedula(numeroCedula);
    }

    public PacienteDTO create(PacienteDTO dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("El ID no debe enviarse en la creación");
        }
        if (dto.getNumeroCedula() == null) {
            throw new IllegalArgumentException("El campo numeroCedula es obligatorio");
        }

        Paciente paciente = mapToDomain(dto);
        paciente = repository.save(paciente);
        return mapToDto(paciente);
    }

    public PacienteDTO update(PacienteDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("El ID es obligatorio para actualizar el paciente");
        }
        if (!repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("No se encontró un paciente con el ID proporcionado");
        }
        Paciente paciente = mapToDomain(dto);
        paciente = repository.save(paciente);
        return mapToDto(paciente);
    }
}
