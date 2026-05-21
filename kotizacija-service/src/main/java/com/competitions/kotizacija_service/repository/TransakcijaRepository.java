package com.competitions.kotizacija_service.repository;

import com.competitions.kotizacija_service.model.Transakcija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransakcijaRepository extends JpaRepository<Transakcija, Long> {
}