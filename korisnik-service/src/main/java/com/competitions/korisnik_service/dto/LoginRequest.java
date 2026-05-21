package com.competitions.korisnik_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Email(message = "Email nije validan")
    @NotNull(message = "Email je obavezan")
    private String email;

    @NotNull(message = "Lozinka je obavezna")
    private String password;
}
