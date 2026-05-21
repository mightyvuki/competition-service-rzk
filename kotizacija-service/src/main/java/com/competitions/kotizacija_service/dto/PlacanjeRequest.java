package com.competitions.kotizacija_service.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlacanjeRequest {
    @Min(1)
    private Long prijavaId;
    @Min(1)
    private Long racunId;
    private BigDecimal iznos;
}
