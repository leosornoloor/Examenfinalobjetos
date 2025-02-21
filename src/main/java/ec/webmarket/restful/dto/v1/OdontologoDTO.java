package ec.webmarket.restful.dto.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdontologoDTO {
    private Long id;
    private String numeroCedula;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String correo;
}