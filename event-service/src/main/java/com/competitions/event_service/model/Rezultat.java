package com.competitions.event_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rezultat")
public class Rezultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @Column(name = "korisnik_id")
    private Long korisnikId;

    @Column(name = "rezultat")
    private Integer rezultat;

    @Column(name = "pozicija")
    private Integer pozicija;


}