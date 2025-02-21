package ec.webmarket.restful.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    
    @ManyToOne
    private Odontologo odontologo;
    
    private boolean disponible; // Nuevo atributo para manejar disponibilidad
}