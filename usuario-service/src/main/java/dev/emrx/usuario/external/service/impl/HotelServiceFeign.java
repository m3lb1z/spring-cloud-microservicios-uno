package dev.emrx.usuario.external.service.impl;

import dev.emrx.usuario.external.client.HotelServiceRest;
import dev.emrx.usuario.external.service.HotelService;
import dev.emrx.usuario.model.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceFeign implements HotelService {

    @Autowired
    private HotelServiceRest hotelService;

    @Override
    public Hotel getHotel(String idHotel) {
        return hotelService.getHotel(idHotel);
    }
}
