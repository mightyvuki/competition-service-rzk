package com.competitions.prijava_service.repository;

import com.competitions.prijava_service.model.Prijava;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrijavaRepository extends JpaRepository<Prijava, Long> {
    List<Prijava> findByDogadjajId(Long dogadjajId);
    List<Prijava> findByKorisnikId(Long korisnikId);
    boolean existsByKorisnikIdAndDogadjajId(Long korisnikId, Long dogadjajId);
}