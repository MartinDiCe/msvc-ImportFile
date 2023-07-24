package com.diceprojects.importcsv.persistences.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileColumnsHeader {
    private Long id;
    private String delimitadorArchivoMapping;
    private String startFile;
    private String operacionProcesoMapping;
    private String tipoEntidadMapping;
    private String tipoOperacionProcesoMapping;
    @JsonProperty("fileColumnsDetails")
    private FileColumnsDetails fileColumnsDetails;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public FileColumnsHeader() {
    }
}
