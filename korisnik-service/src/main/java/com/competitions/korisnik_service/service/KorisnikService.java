package com.competitions.korisnik_service.service;

import com.competitions.korisnik_service.exception.EntityDoesNotExistException;
import com.competitions.korisnik_service.model.Korisnik;
import com.competitions.korisnik_service.repository.KorisnikRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class KorisnikService {
    private final KorisnikRepository korisnikRepository;

    public Korisnik findById(Long id){
        return korisnikRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("Korisnik  [id: " + id + "] ne postoji."));
    }

    public List<Korisnik> vratiKorisnike() {
        return korisnikRepository.findAll();
    }
}
