package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.dto.v1.UsuarioDTO;
import ec.webmarket.restful.service.crud.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> autenticarUsuario(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.autenticarUsuario(dto.getNombreUsuario(), dto.getClave()));
    }

    @PutMapping("/{id}/clave")
    public ResponseEntity<Void> actualizarClave(@PathVariable Long id, @RequestParam String nuevaClave) {
        usuarioService.actualizarClave(id, nuevaClave);
        return ResponseEntity.noContent().build();
    }
}
