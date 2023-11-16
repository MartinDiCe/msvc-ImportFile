package com.diceprojects.importfile.exceptions;

/**
 * Excepción personalizada que representa errores específicos del sistema.
 */
public class CustomException extends RuntimeException {

    /**
     * Mensaje de error asociado a la excepción.
     */
    private final String errorMessage;

    /**
     * Constructor de la excepción.
     *
     * @param errorMessage Mensaje de error asociado a la excepción.
     */
    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Obtiene el mensaje de error asociado a la excepción.
     *
     * @return Mensaje de error.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}

