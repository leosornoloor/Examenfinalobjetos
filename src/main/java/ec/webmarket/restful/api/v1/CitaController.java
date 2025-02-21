package ec.webmarket.restful.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.service.crud.CitaService;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.URI_API_V1_CITA)
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CitaDTO> create(@RequestBody CitaDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable("id") Long id, @RequestBody CitaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable("id") Long id) {
        if (service.cancel(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<CitaDTO>> getHistorialByPaciente(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findByPaciente(id));
    }

    @GetMapping("/odontologo/{id}")
    public ResponseEntity<List<CitaDTO>> getHistorialByOdontologo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findByOdontologo(id));
    }
}
