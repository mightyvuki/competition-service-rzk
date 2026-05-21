package com.competitions.prijava_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DogadjajDTO {
    private Long id;
    private String naziv;
    private String lokacija;
    private LocalDate datum;
    private Integer maxUcesnika;
}
