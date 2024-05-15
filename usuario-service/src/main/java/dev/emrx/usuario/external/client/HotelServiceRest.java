package dev.emrx.usuario.external.client;


import dev.emrx.usuario.model.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServiceRest {

    @GetMapping("/hoteles/{idHotel}")
    Hotel getHotel(@PathVariable String idHotel);
}
