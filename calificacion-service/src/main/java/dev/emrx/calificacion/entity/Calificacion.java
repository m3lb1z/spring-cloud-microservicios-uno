package dev.emrx.calificacion.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idCalificacion"})
@Document(collection = "calificaciones")
public class Calificacion {

    @Id
    private String idCalificacion;
    private String idUsuario;
    private String idHotel;
    private int calificacion;
    private String observaciones;

}
