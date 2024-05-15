package dev.emrx.hotel.controller;

import dev.emrx.hotel.model.entity.Hotel;
import dev.emrx.hotel.model.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> guardarHotel(@RequestBody Hotel hotel, UriComponentsBuilder uriBuilder) {
        hotelService.saveHotel(hotel);
        URI url = uriBuilder.path("/hoteles/{id}").buildAndExpand(hotel.getIdHotel()).toUri();

        return ResponseEntity.created(url).body(hotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> listarHoteles() {
        return ResponseEntity.ok(hotelService.getAllHoteles());
    }

    @GetMapping("/{idHotel}")
    public ResponseEntity<Hotel> obtenerHotel(@PathVariable String idHotel) {
        return ResponseEntity.ok(hotelService.getHotel(idHotel));
    }

}
