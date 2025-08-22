package com.example.service1.exception;

import com.example.service1.dto.OrchestratorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<OrchestratorResponse> handleInvalidJson(HttpMessageNotReadableException ex) {
        log.error("Invalid JSON received: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new OrchestratorResponse("Invalid JSON format", null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OrchestratorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
                .body(new OrchestratorResponse("Internal server error", null));
    }
}
