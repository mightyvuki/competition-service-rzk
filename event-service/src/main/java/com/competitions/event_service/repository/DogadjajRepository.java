package com.competitions.event_service.repository;

import com.competitions.event_service.model.Dogadjaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogadjajRepository extends JpaRepository<Dogadjaj, Long> {

    List<Dogadjaj> findByLokacija(String lokacija);
}