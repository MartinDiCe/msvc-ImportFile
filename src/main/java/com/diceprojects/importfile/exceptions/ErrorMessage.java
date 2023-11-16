package com.diceprojects.importfile.exceptions;

import lombok.Data;
import java.util.Objects;

/**
 * Clase que representa un mensaje de error.
 */
@Data
public class ErrorMessage {

    /**
     * Nombre del campo asociado al mensaje de error.
     */
    private String field;

    /**
     * Mensaje de error.
     */
    private String message;

    /**
     * Constructor de la clase ErrorMessage.
     *
     * @param field   Nombre del campo asociado al mensaje de error.
     * @param message Mensaje de error.
     */
    public ErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * Compara si dos objetos ErrorMessage son iguales basándose en el campo.
     *
     * @param o Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(field, that.field);
    }

    /**
     * Calcula el código hash del objeto basándose en el campo.
     *
     * @return Código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    /**
     * Representación en cadena de la instancia de ErrorMessage.
     *
     * @return Cadena que representa la instancia de ErrorMessage.
     */
    @Override
    public String toString() {
        return "ErrorMessage{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
