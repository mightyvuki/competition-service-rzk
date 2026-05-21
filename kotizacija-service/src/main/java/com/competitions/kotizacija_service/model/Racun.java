package com.competitions.kotizacija_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "racun")
public class Racun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "brojRacuna", length = 50)
    private String brojRacuna;

    @Column(name = "datumKreiranja")
    private LocalDateTime datumKreiranja;

    @Size(max = 50)
    @Column(name = "tipRacuna", length = 50)
    private String tipRacuna;

    @Column(name = "stanje")
    private BigDecimal stanje;

    @OneToMany(mappedBy = "racun")
    private List<Transakcija> transakcije;
}