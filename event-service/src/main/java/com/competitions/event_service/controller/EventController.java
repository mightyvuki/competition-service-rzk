package com.competitions.event_service.controller;

import com.competitions.event_service.dto.RezultatRequest;
import com.competitions.event_service.model.Disciplina;
import com.competitions.event_service.model.Dogadjaj;
import com.competitions.event_service.model.Rezultat;
import com.competitions.event_service.service.EventService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<Dogadjaj>> sviDogadjaji() {
        List<Dogadjaj> dogadjaji = eventService.vratiSveDogadjaje();
        return new ResponseEntity<>(dogadjaji, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dogadjaj> dogadjaj(@PathVariable @Min(1) Long id) {
        Dogadjaj d = eventService.vratiDogadjaj(id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dogadjaj> kreiraj(@RequestBody @Valid Dogadjaj dogadjaj) {
        Dogadjaj d = eventService.kreirajDogadjaj(dogadjaj);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/disciplina")
    public ResponseEntity<Disciplina> dodajDisciplinu(@PathVariable @Min(1) Long id, @RequestBody @Valid Disciplina disciplina) {

        Disciplina d = eventService.dodajDisciplinu(id, disciplina);
        return new ResponseEntity<>(d, HttpStatus.CREATED);
    }

    @GetMapping(params = "lokacija")
    public ResponseEntity<List<Dogadjaj>> filtriraj(@RequestParam("lokacija") @Size(min = 2) String lokacija) {
        List<Dogadjaj> dogadjaji = eventService.filtrirajDogadjaje(lokacija);
        return new ResponseEntity<>(dogadjaji, HttpStatus.OK);
    }

    @Retry(name = "rezultatRetry")
    @PostMapping("/rezultat")
    public ResponseEntity<Rezultat> dodajRezultat(@RequestBody @Valid RezultatRequest rezultat){
        Rezultat r = eventService.dodajRezultat(rezultat);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @GetMapping("/rezultati")
    public ResponseEntity<List<Rezultat>> sviRezultati(){
        List<Rezultat> r = eventService.sviRezultati();
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping("/disciplina/{id}/rezultati")
    public ResponseEntity<List<Rezultat>> poDisciplini(@PathVariable @Min(1) Long id){
        List<Rezultat> r = eventService.rezultatiZaDisciplinu(id);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping("/korisnik/{id}/rezultati")
    public ResponseEntity<List<Rezultat>> poKorisniku(@PathVariable @Min(1) Long id){
        List<Rezultat> r = eventService.rezultatiZaKorisnika(id);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}
