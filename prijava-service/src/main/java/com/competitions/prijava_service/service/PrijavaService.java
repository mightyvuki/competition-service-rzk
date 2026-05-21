package com.competitions.prijava_service.service;

import com.competitions.prijava_service.exception.EntityAlreadyExistsException;
import com.competitions.prijava_service.exception.EntityDoesNotExistException;
import com.competitions.prijava_service.exception.EntityNotValidException;
import com.competitions.prijava_service.feign.EventProxy;
import com.competitions.prijava_service.feign.KorisnikProxy;
import com.competitions.prijava_service.model.Prijava;
import com.competitions.prijava_service.repository.PrijavaRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PrijavaService {

    private final PrijavaRepository prijavaRepository;

    private static final Set<String> VALIDNI = Set.of("PENDING", "APPROVED", "REJECTED");
    private final KorisnikProxy korisnikProxy;
    private final EventProxy eventProxy;

    public Prijava kreirajPrijavu(Prijava prijava){
        try {
            korisnikProxy.korisnik(prijava.getKorisnikId());
        } catch (FeignException.NotFound e) {
            throw new EntityDoesNotExistException("Korisnik [id: " + prijava.getKorisnikId() + "] ne postoji");
        }

        try {
            eventProxy.dogadjaj(prijava.getDogadjajId());
        } catch (FeignException.NotFound e) {
            throw new EntityDoesNotExistException("Event [id: " + prijava.getDogadjajId() + "] ne postoji");
        }

        boolean postoji = prijavaRepository.existsByKorisnikIdAndDogadjajId(
                prijava.getKorisnikId(), prijava.getDogadjajId());
        if (postoji) {
            throw new EntityAlreadyExistsException(
                    "Prijava za korisnika [id: " + prijava.getKorisnikId() + "] za dogadjaj [id: " + prijava.getDogadjajId() + "] vec postoji"
            );
        }
        prijava.setDatum(LocalDateTime.now());
        prijava.setStatus("PENDING");
        return prijavaRepository.save(prijava);
    }

    public void obrisiPrijavu(Long id){
        if (!prijavaRepository.existsById(id)) {
            throw new EntityDoesNotExistException("Prijava [id: " + id + "] ne postoji");
        }
        prijavaRepository.deleteById(id);
    }

    public Prijava updateStatus(Long id, String status) {
        if (!VALIDNI.contains(status.toUpperCase())) {
            throw new EntityNotValidException("Status mora biti PENDING, APPROVED ili REJECTED");
        }

        Prijava prijava = prijavaRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Prijava [id: " + id + "] ne postoji"));

        prijava.setStatus(status.toUpperCase());
        return prijavaRepository.save(prijava);
    }

    public List<Prijava> prijaveZaDogadjaj(Long eventId){
        return prijavaRepository.findByDogadjajId(eventId);
    }

    public List<Prijava> prijaveZaKorisnika(Long userId){
        return prijavaRepository.findByKorisnikId(userId);
    }
}
