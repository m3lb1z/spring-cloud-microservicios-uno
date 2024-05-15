package dev.emrx.usuario.model.service.impl;

import dev.emrx.usuario.model.entity.Calificacion;
import dev.emrx.usuario.model.entity.Hotel;
import dev.emrx.usuario.model.entity.Usuario;
import dev.emrx.usuario.model.exception.ResourceNotFoundException;
import dev.emrx.usuario.model.repository.UsuarioRepository;
import dev.emrx.usuario.model.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomIdUsuario = UUID.randomUUID().toString();
        usuario.setIdUsuario(randomIdUsuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuario(String idUsuario) {
        Usuario usuario = usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + idUsuario));

//        ArrayList<Calificacion> calificacionesDelUsuario = restTemplate
//                .getForObject("http://localhost:8083/calificaciones/usuarios/" + usuario.getIdUsuario(), ArrayList.class);

        Calificacion[] calificacionesDelUsuario = restTemplate
                .getForObject("http://CALIFICACION-SERVICE/calificaciones/usuarios/" + usuario.getIdUsuario(), Calificacion[].class);
//                .getForObject("http://localhost:8083/calificaciones/usuarios/" + usuario.getIdUsuario(), Calificacion[].class);

        List<Calificacion> calificaciones = Arrays.stream(calificacionesDelUsuario).collect(Collectors.toList());
        List<Calificacion> listaCalificaciones = calificaciones.stream()
                .peek(c -> {
                    logger.info("{}", c.getIdHotel());
                })
                .map(calificacion -> {
                    ResponseEntity<Hotel> forEntity = restTemplate
                            .getForEntity("http://HOTEL-SERVICE/hoteles/" + calificacion.getIdHotel(), Hotel.class);
//                            .getForEntity("http://localhost:8082/hoteles/" + calificacion.getIdHotel(), Hotel.class);
                    Hotel hotel = forEntity.getBody();
                    logger.info("Respuesta con codigo de estado: {}", forEntity.getStatusCode());
                    calificacion.setHotel(hotel);

                    return calificacion;
                }).collect(Collectors.toList());

        logger.info("{}", calificacionesDelUsuario);
        usuario.setCalificaciones(listaCalificaciones);

        return usuario;
    }
}
