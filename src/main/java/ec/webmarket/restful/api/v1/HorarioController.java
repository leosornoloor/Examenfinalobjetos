package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.service.crud.HorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.URI_API_V1_HORARIO)
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping
    public ResponseEntity<HorarioDTO> crearHorario(@RequestBody HorarioDTO horarioDTO) {
        return ResponseEntity.ok(horarioService.crearHorario(horarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> actualizarHorario(@PathVariable Long id, @RequestBody HorarioDTO horarioDTO) {
        return ResponseEntity.ok(horarioService.actualizarHorario(id, horarioDTO));
    }

    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<List<HorarioDTO>> obtenerHorariosPorOdontologo(@PathVariable Long odontologoId) {
        return ResponseEntity.ok(horarioService.obtenerHorariosPorOdontologo(odontologoId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<HorarioDTO>> obtenerHorariosPorFecha(@RequestParam String fecha) {
        LocalDate localDate = LocalDate.parse(fecha, DateTimeFormatter.ISO_DATE);
        return ResponseEntity.ok(horarioService.obtenerHorariosPorFecha(localDate));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<HorarioDTO>> obtenerHorariosDisponibles(@RequestParam boolean estado) {
        return ResponseEntity.ok(horarioService.obtenerHorariosDisponibles(estado));
    }
    @GetMapping
    public ResponseEntity<List<HorarioDTO>> obtenerTodosLosHorarios() {
        return ResponseEntity.ok(horarioService.obtenerTodosLosHorarios());
    }

}
