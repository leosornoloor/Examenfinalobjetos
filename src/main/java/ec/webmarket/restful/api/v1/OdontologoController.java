package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.service.crud.CitaService;
import ec.webmarket.restful.service.crud.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.URI_API_V1_ODONTOLOGO)
public class OdontologoController {

    private final OdontologoService odontologoService;
    private final CitaService citaService;

    public OdontologoController(OdontologoService odontologoService, CitaService citaService) {
        this.odontologoService = odontologoService;
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getAll() {
        return ResponseEntity.ok(odontologoService.findAll());
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> create(@RequestBody OdontologoDTO dto) {
        return ResponseEntity.ok(odontologoService.create(dto));
    }

   
    @GetMapping("/{id}/citas")
    public ResponseEntity<List<CitaDTO>> getCitasAsignadas(@PathVariable("id") Long id) {
        return ResponseEntity.ok(citaService.findByOdontologo(id));
    }
    
    @PostMapping("/registro")
    public ResponseEntity<OdontologoDTO> registrarOdontologo(@RequestBody OdontologoDTO dto) {
        return ResponseEntity.ok(odontologoService.create(dto));
    }
}
