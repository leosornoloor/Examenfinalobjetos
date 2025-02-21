package ec.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_PACIENTE })
public class PacienteController {

    @Autowired
    private PacienteService entityService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.findAll(new PacienteDTO())), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PacienteDTO dto) {
        try {
            PacienteDTO createdDto = entityService.create(dto);
            return new ResponseEntity<>(new ApiResponseDTO<>(true, createdDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO<>(false, "Error al crear el paciente: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody PacienteDTO dto) {
        try {
            PacienteDTO updatedDto = entityService.update(dto);
            return new ResponseEntity<>(new ApiResponseDTO<>(true, updatedDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDTO<>(false, "Error al actualizar el paciente: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
