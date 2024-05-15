package dev.emrx.calificacion.service;

import dev.emrx.calificacion.entity.Calificacion;

import java.util.List;

public interface CalificacionService {

    Calificacion saveCalificacion(Calificacion calificacion);
    List<Calificacion> getAllCalificaciones();
    List<Calificacion> getAllCalificacionesByIdUsuario(String idUsuario);
    List<Calificacion> getAllCalificacionesByIdHotel(String idHotel);

}
