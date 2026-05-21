package com.competitions.kotizacija_service.controller;

import com.competitions.kotizacija_service.dto.PlacanjeRequest;
import com.competitions.kotizacija_service.model.Placanje;
import com.competitions.kotizacija_service.service.KotizacijaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kotizacija")
@RequiredArgsConstructor
@Validated
public class KotizacijaController {

    private final KotizacijaService kotizacijaService;

    @PostMapping("/uplata")
    public ResponseEntity<Placanje> izvrsiPlacanje(@RequestBody @Valid PlacanjeRequest request){
        Placanje placanje = kotizacijaService.izvrsiPlacanje(request);
        return new ResponseEntity<>(placanje, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placanje> poId(@PathVariable @Min(1) Long id){
        Placanje placanje = kotizacijaService.nadjiPlacanje(id);
        return new ResponseEntity<>(placanje, HttpStatus.OK);
    }

    @GetMapping("/prijava/{id}")
    public ResponseEntity<Placanje> poPrijavi(@PathVariable @Min(1) Long id){
        Placanje placanje = kotizacijaService.nadjiPoPrijavi(id);
        return new ResponseEntity<>(placanje, HttpStatus.OK);
    }
}
