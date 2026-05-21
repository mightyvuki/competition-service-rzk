package com.competitions.korisnik_service.repository;

import com.competitions.korisnik_service.model.KorisnikUloga;
import com.competitions.korisnik_service.model.KorisnikUlogaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikUlogaRepository extends JpaRepository<KorisnikUloga, KorisnikUlogaId> {
}