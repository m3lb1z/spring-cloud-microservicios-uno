package dev.emrx.hotel.model.repository;

import dev.emrx.hotel.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
