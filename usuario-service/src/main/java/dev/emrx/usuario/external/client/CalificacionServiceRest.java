package dev.emrx.usuario.external.client;

import dev.emrx.usuario.model.entity.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "CALIFICACION-SERVICE")
public interface CalificacionServiceRest {

//    @PostMapping("/calificaciones")
//    public ResponseEntity<Calificacion> guardarCalificacion(Calificacion calificacion);
//
//    @PutMapping("/calificaciones/{idCalificacion}")
//    public ResponseEntity<Calificacion> actualizarCalificacion(@PathVariable String idCalificacion,@RequestBody Calificacion calificacion);

    @GetMapping("/calificaciones/usuarios/{idUsuario}")
    List<Calificacion> getAllCalificacionesPorIdUsuario(@PathVariable String idUsuario);

}
