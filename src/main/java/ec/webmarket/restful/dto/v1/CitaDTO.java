package ec.webmarket.restful.dto.v1;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CitaDTO {
    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private Long horarioId;
    private LocalDateTime fechaHora;
    private String motivoConsulta;
    private String estado;
}