package dev.emrx.usuario.external.service.impl;

import dev.emrx.usuario.external.client.CalificacionServiceRest;
import dev.emrx.usuario.external.service.CalificacionService;
import dev.emrx.usuario.model.entity.Calificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServiceFeign implements CalificacionService {

    @Autowired
    private CalificacionServiceRest calificacionServiceRest;

    @Override
    public List<Calificacion> getAllCalificacionesPorIdUsuario(String idUsuario) {
        System.out.println("CalificacionServiceFeign: getAllCalificacionesPorIdUsuario");

        return calificacionServiceRest.getAllCalificacionesPorIdUsuario(idUsuario);
    }
}
