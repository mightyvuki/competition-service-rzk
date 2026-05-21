package com.competitions.event_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorEntity {
    private String message;
    private LocalDateTime dateTime;
}
