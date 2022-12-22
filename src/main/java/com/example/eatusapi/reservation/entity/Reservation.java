package com.example.eatusapi.reservation.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Setter
@DynamicInsert
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotNull
    String placename;
    @NotNull
    Date date;
    @NotNull
    String food;
    @NotNull
    int currentmember;
    @Column(name = "member_1")
    String member1;
    @Column(name = "member_2")
    String member2;
    @Column(name = "member_3")
    String member3;
    @Column(name = "member_4")
    String member4;
    @Column(name = "member_5")
    String member5;
}
