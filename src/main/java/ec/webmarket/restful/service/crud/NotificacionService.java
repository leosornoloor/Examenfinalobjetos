package ec.webmarket.restful.service.crud;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.*;
import ec.webmarket.restful.dto.v1.*;
import ec.webmarket.restful.persistence.*;
import ec.webmarket.restful.service.GenericCrudServiceImpl;




@Service
public class NotificacionService extends GenericCrudServiceImpl<Notificacion, NotificacionDTO> {
    @Autowired
    private NotificacionRepository repository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Notificacion> find(NotificacionDTO dto) {
        return repository.findById(dto.getId());
    }

    @Override
    public Notificacion mapToDomain(NotificacionDTO dto) {
        return modelMapper.map(dto, Notificacion.class);
    }

    @Override
    public NotificacionDTO mapToDto(Notificacion domain) {
        return modelMapper.map(domain, NotificacionDTO.class);
    }

    public void enviarNotificacion(NotificacionDTO dto) {
        Notificacion notificacion = mapToDomain(dto);
        repository.save(notificacion);
    }
}

