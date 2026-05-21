package com.competitions.prijava_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "prijava")
public class Prijava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "korisnik_id")
    private Long korisnikId;

    @Column(name = "dogadjaj_id")
    private Long dogadjajId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "datum")
    private LocalDateTime datum;

    @Size(max = 30)
    @Column(name = "status", length = 30)
    private String status;


}