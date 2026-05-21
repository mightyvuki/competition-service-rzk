package com.competitions.prijava_service.repository;

import com.competitions.prijava_service.model.Tim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimRepository extends JpaRepository<Tim, Long> {
}