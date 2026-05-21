package com.competitions.event_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "dogadjaj")
public class Dogadjaj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "naziv", length = 100)
    private String naziv;

    @Size(max = 100)
    @Column(name = "lokacija", length = 100)
    private String lokacija;

    @Column(name = "datum")
    private LocalDate datum;

    @Column(name = "maxUcesnika")
    private Integer maxUcesnika;


}