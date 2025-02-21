package ec.webmarket.restful.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String numeroCedula;
    
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String correo;
    
    @OneToMany(mappedBy = "odontologo")
    private List<Cita> citas;
}