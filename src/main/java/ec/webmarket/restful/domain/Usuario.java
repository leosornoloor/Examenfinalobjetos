package ec.webmarket.restful.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private String clave;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario; // Puede ser ODONTOLOGO o PACIENTE

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Paciente paciente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Odontologo odontologo;

    public enum TipoUsuario {
        ODONTOLOGO, PACIENTE
    }
}
