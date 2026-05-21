package com.competitions.korisnik_service.controller;

import com.competitions.korisnik_service.dto.LoginRequest;
import com.competitions.korisnik_service.dto.RegisterRequest;
import com.competitions.korisnik_service.model.Korisnik;
import com.competitions.korisnik_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Korisnik> register(@Valid @RequestBody RegisterRequest request){
        Korisnik korisnik = authService.register(request);
        return new ResponseEntity<>(korisnik, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Korisnik> login(@Valid @RequestBody LoginRequest request){
        Korisnik korisnik = authService.login(request);
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }
}