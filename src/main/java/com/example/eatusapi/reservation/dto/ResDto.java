package com.example.eatusapi.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
@Getter
@Setter
public class ResDto {
    String placename;
    Date date;
    String food;
    String member;
}
