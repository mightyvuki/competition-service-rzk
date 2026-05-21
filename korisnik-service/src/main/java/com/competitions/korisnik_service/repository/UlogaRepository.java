package com.competitions.korisnik_service.repository;

import com.competitions.korisnik_service.model.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UlogaRepository extends JpaRepository<Uloga, Long> {
    Optional<Uloga> findByNaziv(String naziv);
}