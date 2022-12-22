package com.example.eatusapi.reservation.service;

import com.example.eatusapi.reservation.dto.ResDto;
import com.example.eatusapi.reservation.dto.ResQueryDto;
import com.example.eatusapi.reservation.entity.Reservation;
import com.example.eatusapi.reservation.mapper.ResMapper;
import com.example.eatusapi.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public Reservation reserve(ResDto resDto) {
        Reservation prereservation;
        try {
            prereservation = reservationRepository.findReservationByDateAndPlacename(resDto.getDate(), resDto.getPlacename()).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            Reservation reservation = ResMapper.mapping(resDto);
            reservation.setMember1(resDto.getMember());
            return reservationRepository.save(reservation);
        }
        return insertInNullSpace(prereservation, resDto);
    }

    public Reservation cancel(ResDto resDto) {
        Reservation prereservation = reservationRepository.findReservationByDateAndPlacename(resDto.getDate(), resDto.getPlacename()).orElseThrow(NoSuchElementException::new);

        return deleteMembername(prereservation, resDto);
    }

    public Reservation getResrvation(ResQueryDto resQueryDto) {
        return reservationRepository.findReservationByDateAndPlacename(resQueryDto.getDate(), resQueryDto.getPlacename()).orElseThrow(NoSuchElementException::new);
    }

    private Reservation insertInNullSpace(Reservation prereservation, ResDto resDto) {
        if(prereservation.getMember1() == null) {
            prereservation.setMember1(resDto.getMember());
            prereservation.setCurrentmember(prereservation.getCurrentmember()+1);
            return reservationRepository.save(prereservation);
        }
        if(prereservation.getMember2() == null) {
            prereservation.setMember2(resDto.getMember());
            prereservation.setCurrentmember(prereservation.getCurrentmember()+1);
            return reservationRepository.save(prereservation);
        }
        if(prereservation.getMember3() == null) {
            prereservation.setMember3(resDto.getMember());
            prereservation.setCurrentmember(prereservation.getCurrentmember()+1);
            return reservationRepository.save(prereservation);
        }
        if(prereservation.getMember4() == null) {
            prereservation.setMember4(resDto.getMember());
            prereservation.setCurrentmember(prereservation.getCurrentmember()+1);
            return reservationRepository.save(prereservation);
        }
        if(prereservation.getMember5() == null) {
            prereservation.setMember5(resDto.getMember());
            prereservation.setCurrentmember(prereservation.getCurrentmember()+1);
            return reservationRepository.save(prereservation);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private Reservation deleteMembername(Reservation prereservation, ResDto resDto) {
        String name = resDto.getMember();
        if(name.equals(prereservation.getMember1())) {
            prereservation.setMember1(null);
            prereservation.setCurrentmember(prereservation.getCurrentmember()-1);
            return reservationRepository.save(prereservation);
        }
        if(name.equals(prereservation.getMember2())) {
            prereservation.setMember2(null);
            prereservation.setCurrentmember(prereservation.getCurrentmember()-1);
            return reservationRepository.save(prereservation);
        }
        if(name.equals(prereservation.getMember3())) {
            prereservation.setMember3(null);
            prereservation.setCurrentmember(prereservation.getCurrentmember()-1);
            return reservationRepository.save(prereservation);
        }
        if(name.equals(prereservation.getMember4())) {
            prereservation.setMember4(null);
            prereservation.setCurrentmember(prereservation.getCurrentmember()-1);
            return reservationRepository.save(prereservation);
        }
        if(name.equals(prereservation.getMember5())) {
            prereservation.setMember5(null);
            prereservation.setCurrentmember(prereservation.getCurrentmember()-1);
            return reservationRepository.save(prereservation);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

}
