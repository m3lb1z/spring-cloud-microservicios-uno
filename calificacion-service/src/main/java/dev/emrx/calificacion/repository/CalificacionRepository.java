package dev.emrx.calificacion.repository;

import dev.emrx.calificacion.entity.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CalificacionRepository extends MongoRepository<Calificacion, String> {


    List<Calificacion> findByIdUsuario(String idUsuario);

    List<Calificacion> findByIdHotel(String idHotel);

}
