package com.product.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Clase que define la estructura de respuesta para las excepciones.
 * Contiene toda la información necesaria para informar al cliente sobre el error.
 */
public class ExceptionResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    
    private Integer status;
    private HttpStatus error;
    private String message;
    private String path;

    // Constructor por defecto
    public ExceptionResponse() {
    }

    // Constructor con parámetros
    public ExceptionResponse(LocalDateTime timestamp, Integer status, HttpStatus error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Getters y Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}