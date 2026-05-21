package com.competitions.event_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "naziv", length = 100)
    private String naziv;

    @ManyToOne
    @JoinColumn(name = "dogadjaj_id")
    private Dogadjaj dogadjaj;

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnore
    private Set<Rezultat> rezultats = new LinkedHashSet<>();


}