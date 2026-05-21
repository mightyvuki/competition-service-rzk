package com.competitions.korisnik_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class KorisnikUlogaId implements Serializable {
    private static final long serialVersionUID = -8335593489465190539L;
    @NotNull
    @Column(name = "korisnik_id", nullable = false)
    private Long korisnikId;

    @NotNull
    @Column(name = "uloga_id", nullable = false)
    private Long ulogaId;


}