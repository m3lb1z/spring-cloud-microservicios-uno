package dev.emrx.usuario.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(of = "idUsuario")
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private String idUsuario;
    @Column(name = "nombre", length = 20)
    private String nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "informacion")
    private String informacion;
    @Transient
    private List<Calificacion> calificaciones = new ArrayList<>();


}
