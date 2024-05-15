package dev.emrx.calificacion.service.impl;

import dev.emrx.calificacion.entity.Calificacion;
import dev.emrx.calificacion.repository.CalificacionRepository;
import dev.emrx.calificacion.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion saveCalificacion(Calificacion calificacion) {
//        String idCalificacion = UUID.randomUUID().toString();
//        calificacion.setIdCalificacion(idCalificacion);

        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> getAllCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public List<Calificacion> getAllCalificacionesByIdUsuario(String idUsuario) {
        return calificacionRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public List<Calificacion> getAllCalificacionesByIdHotel(String idHotel) {
        return calificacionRepository.findByIdHotel(idHotel);
    }
}
