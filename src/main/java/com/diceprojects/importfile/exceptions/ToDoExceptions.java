package com.diceprojects.importfile.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Clase de excepción personalizada para manejar errores específicos de la aplicación.
 */
@Data
public class ToDoExceptions extends RuntimeException {

    private String message;

    private HttpStatus httpStatus;

    /**
     * Constructor de la clase ToDoExceptions.
     *
     * @param message    Mensaje de la excepción.
     * @param httpStatus Estado HTTP asociado a la excepción.
     */
    public ToDoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
