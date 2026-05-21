package com.competitions.kotizacija_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "placanje")
public class Placanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "prijava_id")
    private Long prijavaId;

    @Column(name = "racun_id")
    private Long racunId;

    @Size(max = 30)
    @Column(name = "status", length = 30)
    private String status;


}