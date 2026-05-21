package com.competitions.korisnik_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "korisnik_uloga")
public class KorisnikUloga {
    @EmbeddedId
    private KorisnikUlogaId id;

    @MapsId("korisnikId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @MapsId("ulogaId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "uloga_id", nullable = false)
    private Uloga uloga;


}