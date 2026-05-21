package com.competitions.event_service.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class RezultatRequest {
    @Min(1)
    private long disciplinaId;
    @Min(1)
    private long korisnikId;
    @Min(0)
    private int rezultat;
}
