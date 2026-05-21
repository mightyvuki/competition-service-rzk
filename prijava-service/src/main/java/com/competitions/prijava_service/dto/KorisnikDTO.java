package com.competitions.prijava_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KorisnikDTO {

    @NotNull(message = "Ime je obavezno")
    private String ime;

    @NotNull(message = "Prezime je obavezno")
    private String prezime;

    @NotNull(message = "Datum rodjenja je obavezan")
    private String datumRodjenja;

    @Email(message = "Email nije validan")
    @NotBlank(message = "Email je obavezan")
    private String email;

    @Size(min = 4, message = "Lozinka mora imati minimum 4 karaktera")
    private String password;
}
