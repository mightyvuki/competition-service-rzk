package com.competitions.kotizacija_service.repository;

import com.competitions.kotizacija_service.model.Placanje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlacanjeRepository extends JpaRepository<Placanje, Long> {
    Optional<Placanje> findByPrijavaId(Long prijavaId);
}