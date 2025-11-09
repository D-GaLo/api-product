package com.product.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para manejar errores específicos de la API.
 * Extiende RuntimeException para poder ser lanzada sin necesidad de try-catch obligatorio.
 */
public class ApiException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    /**
     * Constructor para crear una excepción de API.
     * 
     * @param status Código de estado HTTP del error
     * @param message Descripción del error
     */
    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    // Getters y Setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}