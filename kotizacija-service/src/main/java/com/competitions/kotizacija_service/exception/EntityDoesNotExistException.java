package com.competitions.kotizacija_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntityDoesNotExistException extends RuntimeException {
    private String message;
}
