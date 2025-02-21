package ec.webmarket.restful.dto.v1;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class NotificacionDTO {
    private Long id;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private Long pacienteId;
    private LocalDateTime fechaCreacion;
}
