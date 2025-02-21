package ec.webmarket.restful.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;
    
    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario horario;

    private LocalDateTime fechaHora; // Fecha y hora de la cita
    private String motivoConsulta;   // Motivo de la consulta

    @Enumerated(EnumType.STRING)
    private EstadoCita estado; // Estado de la cita (Confirmada, Cancelada, Reprogramada)

    public enum EstadoCita {
        CONFIRMADA, CANCELADA, REPROGRAMADA
    }
}