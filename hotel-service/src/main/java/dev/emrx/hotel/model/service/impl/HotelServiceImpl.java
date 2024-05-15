package dev.emrx.hotel.model.service.impl;

import dev.emrx.hotel.model.entity.Hotel;
import dev.emrx.hotel.model.exception.ResourceNotFoundException;
import dev.emrx.hotel.model.repository.HotelRepository;
import dev.emrx.hotel.model.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String idHotel = UUID.randomUUID().toString();
        hotel.setIdHotel(idHotel);

        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHoteles() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String idHotel) {
        return hotelRepository
                .findById(idHotel)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel no encontrado con el ID: " + idHotel));
    }
}
