package dev.emrx.usuario.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String idHotel;
    private String nombre;
    private String ubicacion;
    private String informacion;

}
