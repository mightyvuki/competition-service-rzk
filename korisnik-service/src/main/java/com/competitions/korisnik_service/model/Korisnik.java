package com.competitions.korisnik_service.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@Getter
@Setter
@Entity
@Table(name = "korisnik")
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "ime", length = 50)
    private String ime;

    @Size(max = 50)
    @Column(name = "prezime", length = 50)
    private String prezime;

    @Column(name = "datumRodjenja")
    private LocalDate datumRodjenja;

    @Size(max = 100)
    @NotNull
    @Email
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password")
    private String password;


}