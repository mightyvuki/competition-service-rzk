package com.competitions.event_service.exception;

import com.competitions.event_service.exception.EntityAlreadyExistsException;
import com.competitions.event_service.exception.EntityDoesNotExistException;
import com.competitions.event_service.exception.EntityNotValidException;
import com.competitions.event_service.exception.ErrorEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Broj errora: ").append(ex.getErrorCount()).append(", ");
        for (FieldError e : ex.getFieldErrors()) {
            stringBuilder.append("Polje: ").append(e.getField()).append(" - ").append(e.getDefaultMessage()).append(", ");
        }

        ErrorEntity errorEntity = new ErrorEntity(stringBuilder.toString(), LocalDateTime.now());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityDoesNotExistException.class)
    public final ResponseEntity<ErrorEntity> handleEntityDoesNotExist(EntityDoesNotExistException ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public final ResponseEntity<ErrorEntity> handleEntityAlreadyExists(EntityAlreadyExistsException ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorEntity, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotValidException.class)
    public final ResponseEntity<ErrorEntity> handleEntityNotValid(EntityNotValidException ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
