package dev.emrx.hotel.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idHotel"})
@Entity
@Table(name = "hoteles")
public class Hotel {
    @Id
    private String idHotel;
    private String nombre;
    private String informacion;
    private String ubicacion;
}
