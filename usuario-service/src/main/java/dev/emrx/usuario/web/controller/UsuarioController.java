package dev.emrx.usuario.web.controller;

import dev.emrx.usuario.model.entity.Usuario;
import dev.emrx.usuario.model.service.UsuarioService;
import feign.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario datos, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.saveUsuario(datos);
        URI url = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getIdUsuario()).toUri();

        return ResponseEntity.created(url).body(usuario);
    }

    int cantidadReintentos = 1;

    @GetMapping("/{idUsuario}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String idUsuario) {
        log.info("Listar un solo usuario: UsuarioController");
        log.info("Cantidad de reintentos: {}", cantidadReintentos++);
        Usuario usuario = usuarioService.getUsuario(idUsuario);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        return ResponseEntity.ok(usuarios);
    }

    private ResponseEntity<Usuario> ratingHotelFallback(String idUsuario, Exception exception) {
        log.info("El respaldo se ejecuta porque el servicio esta inactivo: ", exception.getMessage());
        Usuario usuario = Usuario.builder()
                .email("root1@gmail.com")
                .nombre("root")
                .informacion("Este usuario se crea por defecto cuando un servicio se cae")
                .idUsuario("1234")
                .build();

        return ResponseEntity.ok(usuario);
    }

}
