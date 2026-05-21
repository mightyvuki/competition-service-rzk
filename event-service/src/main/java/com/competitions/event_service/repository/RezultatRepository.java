package com.competitions.event_service.repository;

import com.competitions.event_service.model.Rezultat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RezultatRepository extends JpaRepository<Rezultat, Long> {
    List<Rezultat> findByDisciplinaId(Long disciplinaId);
    List<Rezultat> findByKorisnikId(Long korisnikId);
}