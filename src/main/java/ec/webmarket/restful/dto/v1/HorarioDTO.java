package ec.webmarket.restful.dto.v1;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class HorarioDTO {
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Long odontologoId;
    private boolean disponible; 
}