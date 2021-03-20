package com.vis.test.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

/**
 * This handler sets response status to 404
 * whenever it encounters an EntityNotFoundException
 */
@ControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleConflict() {
        // Nothing to do
    }
}
