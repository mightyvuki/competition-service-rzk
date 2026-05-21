package com.competitions.prijava_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EntityNotValidException extends RuntimeException {
    String message;
}
