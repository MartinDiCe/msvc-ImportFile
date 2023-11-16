package com.diceprojects.importfile.exceptions;

/**
 * Excepción lanzada cuando no se encuentran configuraciones de columnas para un archivo específico.
 */
public class ColumnsNoEncontradasException extends RuntimeException {

    /**
     * Nombre del archivo para el cual no se encontraron configuraciones de columnas.
     */
    private String fileName;

    /**
     * Constructor de la excepción.
     *
     * @param fileName Nombre del archivo para el cual no se encontraron configuraciones de columnas.
     */
    public ColumnsNoEncontradasException(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Obtiene el nombre del archivo para el cual no se encontraron configuraciones de columnas.
     *
     * @return Nombre del archivo.
     */
    public String getFileName() {
        return fileName;
    }
}

