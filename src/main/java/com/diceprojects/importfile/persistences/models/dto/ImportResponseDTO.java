package com.diceprojects.importfile.persistences.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la respuesta de la importación de un archivo.
 */
@Getter
@Setter
public class ImportResponseDTO {

    public ImportResponseDTO(String title, String status, String detail) {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    private String title;
    private String status;
    private String detail;

    public ImportResponseDTO() {

    }
}