package com.example.eatusapi.reservation.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
public class ResQueryDto {
    private Date date;
    private String placename;
}
