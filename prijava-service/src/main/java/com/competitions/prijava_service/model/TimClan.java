package com.competitions.prijava_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tim_clan")
public class TimClan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tim_id")
    private Long tim;

    @Column(name = "korisnik_id")
    private Long korisnikId;


}