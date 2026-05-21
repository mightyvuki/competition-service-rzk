package com.competitions.event_service.service;

import com.competitions.event_service.dto.RezultatRequest;
import com.competitions.event_service.exception.EntityDoesNotExistException;
import com.competitions.event_service.exception.EntityNotValidException;
import com.competitions.event_service.model.Disciplina;
import com.competitions.event_service.model.Dogadjaj;
import com.competitions.event_service.model.Rezultat;
import com.competitions.event_service.repository.DisciplinaRepository;
import com.competitions.event_service.repository.DogadjajRepository;
import com.competitions.event_service.repository.RezultatRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final DogadjajRepository dogadjajRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final RezultatRepository rezultatRepository;

    public List<Dogadjaj> vratiSveDogadjaje() {
        return dogadjajRepository.findAll();
    }

    public Dogadjaj kreirajDogadjaj(Dogadjaj dogadjaj) {
        if (dogadjaj.getNaziv() == null || dogadjaj.getNaziv().isBlank()) {
            throw new EntityNotValidException("Naziv dogadjaja je obavezan");
        }
        return dogadjajRepository.save(dogadjaj);
    }

    public Disciplina dodajDisciplinu(Long dogadjajId, Disciplina disciplina) {
        Dogadjaj dogadjaj = dogadjajRepository.findById(dogadjajId)
                .orElseThrow(() -> new EntityDoesNotExistException("Dogadjaj [id: " + dogadjajId + "] ne postoji"));

        if (disciplina.getNaziv() == null || disciplina.getNaziv().isBlank()) {
            throw new EntityNotValidException("Naziv discipline je obavezan");
        }

        disciplina.setDogadjaj(dogadjaj);

        return disciplinaRepository.save(disciplina);
    }

    public List<Dogadjaj> filtrirajDogadjaje(String lokacija) {
        return dogadjajRepository.findByLokacija(lokacija);
    }


    public Rezultat dodajRezultat(RezultatRequest rezultat){
        Optional<Disciplina> disciplina = disciplinaRepository.findById(rezultat.getDisciplinaId());
        if(disciplina.isEmpty()){
            throw new EntityDoesNotExistException("Disciplina [id: " + rezultat.getDisciplinaId() + "] ne postoji");
        }
        try {
            Rezultat r = new Rezultat();
            r.setRezultat(rezultat.getRezultat());
            r.setDisciplina(disciplina.get());
            r.setKorisnikId(rezultat.getKorisnikId());
            return rezultatRepository.save(r);
        } catch (EntityNotValidException e) {
            throw new EntityNotValidException("Niste dobro poslali rezultat");
        }
    }

    public List<Rezultat> sviRezultati(){
        return rezultatRepository.findAll();
    }

    public List<Rezultat> rezultatiZaDisciplinu(Long disciplinaId){
        return rezultatRepository.findByDisciplinaId(disciplinaId);
    }

    public List<Rezultat> rezultatiZaKorisnika(Long korisnikId){
        return rezultatRepository.findByKorisnikId(korisnikId);
    }

    public Dogadjaj vratiDogadjaj(Long id) {
        return dogadjajRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Dogadjaj [id: " + id + "] ne postoji"));
    }
}
