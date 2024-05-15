package dev.emrx.hotel.model.service;

import dev.emrx.hotel.model.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHoteles();
    Hotel getHotel(String idHotel);

}
