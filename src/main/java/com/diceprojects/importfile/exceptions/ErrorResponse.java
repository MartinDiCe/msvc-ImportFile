package com.diceprojects.importfile.exceptions;

/**
 * Clase que representa una respuesta de error.
 */
public class ErrorResponse {

    /**
     * Mensaje de error.
     */
    private String mensaje;

    /**
     * Constructor de la clase ErrorResponse.
     *
     * @param mensaje Mensaje de error.
     */
    public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el mensaje de error.
     *
     * @return Mensaje de error.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de error.
     *
     * @param mensaje Mensaje de error.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
