package ec.webmarket.restful.domain;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private Long numeroCedula;
    
    @Column(length = 50, nullable = false)
    private String nombre;
    
    @Column(length = 50, nullable = false)
    private String apellido;
    
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column(nullable = true)
    private String telefono;
    
    @Column(nullable = true)
    private String direccionResidencia;
    
    @Column(nullable = true)
    private String email;
}
