package com.competitions.prijava_service.controller;

import com.competitions.prijava_service.model.Prijava;
import com.competitions.prijava_service.service.PrijavaService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prijava")
@RequiredArgsConstructor
@Validated
public class PrijavaController {

    private final PrijavaService prijavaService;

    @PostMapping
    @RateLimiter(name = "prijavaRateLimiter")
    public ResponseEntity<Prijava> prijava(@RequestBody @Valid Prijava prijava){
        Prijava kreirana = prijavaService.kreirajPrijavu(prijava);
        return new ResponseEntity<>(kreirana, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable @Min(1) Long id){
        prijavaService.obrisiPrijavu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Prijava>> poDogadjaju(@PathVariable @Min(1) Long eventId){
        List<Prijava> prijave = prijavaService.prijaveZaDogadjaj(eventId);
        return new ResponseEntity<>(prijave, HttpStatus.OK);
    }

    @GetMapping("/korisnik/{userId}")
    public ResponseEntity<List<Prijava>> poKorisniku(@PathVariable @Min(1) Long userId){
        List<Prijava> prijave = prijavaService.prijaveZaKorisnika(userId);
        return new ResponseEntity<>(prijave, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Prijava> updateStatus(@PathVariable @Min(1) Long id, @RequestParam String status) {

        Prijava updated = prijavaService.updateStatus(id, status);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
