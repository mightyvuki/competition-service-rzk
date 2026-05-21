package com.competitions.kotizacija_service.repository;

import com.competitions.kotizacija_service.model.Racun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacunRepository extends JpaRepository<Racun, Long> {
}