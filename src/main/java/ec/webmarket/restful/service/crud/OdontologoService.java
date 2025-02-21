package ec.webmarket.restful.service.crud;

import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.persistence.OdontologoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoService {

    private final OdontologoRepository repository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository repository) {
        this.repository = repository;
        this.modelMapper = new ModelMapper();
    }

    public OdontologoDTO create(OdontologoDTO dto) {
        Odontologo odontologo = modelMapper.map(dto, Odontologo.class);
        Odontologo savedOdontologo = repository.save(odontologo);
        return modelMapper.map(savedOdontologo, OdontologoDTO.class);
    }

    public List<OdontologoDTO> findAll() {
        return repository.findAll().stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoDTO.class))
                .collect(Collectors.toList());
    }

    public OdontologoDTO findById(Long id) {
        Optional<Odontologo> optionalOdontologo = repository.findById(id);
        return optionalOdontologo.map(odontologo -> modelMapper.map(odontologo, OdontologoDTO.class)).orElse(null);
    }
}