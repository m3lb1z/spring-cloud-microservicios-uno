package dev.emrx.usuario.external.service;

import dev.emrx.usuario.model.entity.Calificacion;

import java.util.List;

public interface CalificacionService {

    List<Calificacion> getAllCalificacionesPorIdUsuario(String idUsuario);


}
