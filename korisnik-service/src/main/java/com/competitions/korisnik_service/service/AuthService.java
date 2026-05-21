package com.competitions.korisnik_service.service;

import com.competitions.korisnik_service.dto.LoginRequest;
import com.competitions.korisnik_service.dto.RegisterRequest;
import com.competitions.korisnik_service.exception.EntityAlreadyExistsException;
import com.competitions.korisnik_service.exception.EntityDoesNotExistException;
import com.competitions.korisnik_service.exception.EntityNotValidException;
import com.competitions.korisnik_service.model.Korisnik;
import com.competitions.korisnik_service.model.KorisnikUloga;
import com.competitions.korisnik_service.model.KorisnikUlogaId;
import com.competitions.korisnik_service.model.Uloga;
import com.competitions.korisnik_service.repository.KorisnikRepository;
import com.competitions.korisnik_service.repository.KorisnikUlogaRepository;
import com.competitions.korisnik_service.repository.UlogaRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class AuthService {
    private final KorisnikRepository korisnikRepository;
    private final UlogaRepository ulogaRepository;
    private final KorisnikUlogaRepository korisnikUlogaRepository;


    public Korisnik register(RegisterRequest request){

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(request.getIme());
        korisnik.setPrezime(request.getPrezime());
        korisnik.setDatumRodjenja(LocalDate.parse(request.getDatumRodjenja()));
        korisnik.setEmail(request.getEmail());
        korisnik.setPassword(request.getPassword());

        try {
            Korisnik saved = korisnikRepository.save(korisnik);
            Uloga uloga = ulogaRepository.findByNaziv("USER").orElseThrow(() -> new EntityDoesNotExistException("Uloga ne postoji."));

            KorisnikUlogaId id = new KorisnikUlogaId();
            id.setKorisnikId(saved.getId());
            id.setUlogaId(uloga.getId());

            KorisnikUloga ku = new KorisnikUloga();
            ku.setId(id);
            ku.setKorisnik(saved);
            ku.setUloga(uloga);

            korisnikUlogaRepository.save(ku);

            return saved;
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Korisnik [email: " + request.getEmail() + "] vec postoji.");
        }
        catch (ConstraintViolationException e) {
            throw new EntityNotValidException("Niste pravilno uneli podatke. Molimo proverite i pokusajte ponovo.");
        }

    }

    public Korisnik login(LoginRequest request){

        Korisnik korisnik = korisnikRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityDoesNotExistException("Korisnik [email:" + request.getEmail() + "] ne postoji."));

        if(!korisnik.getPassword().equals(request.getPassword())){
            throw new EntityNotValidException("Pogresna lozinka. Pokusajte ponovo.");
        }

        return korisnik;
    }
}
