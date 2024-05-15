package dev.emrx.usuario.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calificacion {

    private String idCalificacion;
    private String idUsuario;
    private String idHotel;
    private int calificacion;
    private String observaciones;

    private Hotel hotel;

}
