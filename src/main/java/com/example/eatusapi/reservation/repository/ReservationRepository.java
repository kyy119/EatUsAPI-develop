package com.example.eatusapi.reservation.repository;

import com.example.eatusapi.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findReservationByDateAndPlacename(Date date, String place);
}
