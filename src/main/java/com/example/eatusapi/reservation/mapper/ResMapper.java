package com.example.eatusapi.reservation.mapper;

import com.example.eatusapi.reservation.dto.ResDto;
import com.example.eatusapi.reservation.entity.Reservation;

public class ResMapper {
    public static Reservation mapping(ResDto res) {
        return Reservation.builder()
                .placename(res.getPlacename())
                .date(res.getDate())
                .food(res.getFood())
                .currentmember(1)
                .build();
    }
}
