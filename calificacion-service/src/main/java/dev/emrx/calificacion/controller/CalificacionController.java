package dev.emrx.calificacion.controller;


import dev.emrx.calificacion.entity.Calificacion;
import dev.emrx.calificacion.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;


    @PostMapping
    public ResponseEntity<Calificacion> guardarCalificacion(@RequestBody Calificacion datos, UriComponentsBuilder uriBuilder) {
        Calificacion calificacion = calificacionService.saveCalificacion(datos);
        URI url = uriBuilder.path("/calificaciones/{id}").buildAndExpand(calificacion.getIdCalificacion()).toUri();

        return ResponseEntity.created(url).body(calificacion);
    }

    @GetMapping
    public ResponseEntity<List<Calificacion>> listarCalificaciones() {
        return ResponseEntity.ok(calificacionService.getAllCalificaciones());
    }

    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<List<Calificacion>> listarCalificacionesPorIdUsuario(@PathVariable String idUsuario) {
        return ResponseEntity.ok(calificacionService.getAllCalificacionesByIdUsuario(idUsuario));
    }

    @GetMapping("/hoteles/{idHotel}")
    public ResponseEntity<List<Calificacion>> listarCalificacionesPorIdHotel(@PathVariable String idHotel) {
        return ResponseEntity.ok(calificacionService.getAllCalificacionesByIdHotel(idHotel));
    }

}
