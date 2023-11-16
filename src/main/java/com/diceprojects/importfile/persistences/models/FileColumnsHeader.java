package com.diceprojects.importfile.persistences.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Clase que representa la configuración de las columnas de un archivo.
 */
@Data
public class FileColumnsHeader {

    /**
     * Identificador único de la configuración.
     */
    private Long id;

    /**
     * Delimitador utilizado en el archivo.
     */
    private String delimitadorArchivoMapping;

    /**
     * Prefijo del nombre del archivo para coincidir con la configuración.
     */
    private String startFile;

    /**
     * Mapeo de la operación o proceso del archivo.
     */
    private String operacionProcesoMapping;

    /**
     * Tipo de entidad mapeada.
     */
    private String tipoEntidadMapping;

    /**
     * Tipo de operación o proceso mapeado.
     */
    private String tipoOperacionProcesoMapping;

    /**
     * Ignora la primera fila del archivo en true.
     */
    private Boolean ignoreFirstRowMapping;

    /**
     * Detalles de las columnas del archivo.
     */
    @JsonProperty("fileColumnsDetails")
    private FileColumnsDetails fileColumnsDetails;

    /**
     * Fecha de creación de la configuración.
     */
    private LocalDateTime createDate;

    /**
     * Fecha de última actualización de la configuración.
     */
    private LocalDateTime updateDate;

    /**
     * Constructor por defecto.
     */
    public FileColumnsHeader() {
    }
}

