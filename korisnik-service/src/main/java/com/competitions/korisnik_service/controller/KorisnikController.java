package com.competitions.korisnik_service.controller;

import com.competitions.korisnik_service.model.Korisnik;
import com.competitions.korisnik_service.service.KorisnikService;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/korisnici")
@Validated
public class KorisnikController {

    private final KorisnikService korisnikService;

    @GetMapping("/{id}")
    public ResponseEntity<Korisnik> korisnik(@PathVariable @Min(1) Long id){
        Korisnik korisnik = korisnikService.findById(id);
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Korisnik>> korisnici(){
        List<Korisnik> korisnici = korisnikService.vratiKorisnike();
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }
}
