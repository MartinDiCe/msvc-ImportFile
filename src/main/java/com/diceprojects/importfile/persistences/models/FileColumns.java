package com.diceprojects.importfile.persistences.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa las columnas de un archivo, incluyendo la cabecera y los detalles.
 */
@Getter
@Setter
public class FileColumns {

    private FileColumnsHeader header;
    private FileColumnsDetails details;

    public FileColumns() {
    }
}
