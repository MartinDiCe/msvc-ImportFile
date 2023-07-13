package com.diceprojects.importcsv.persistences.models;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "importacion_archivo")
public class FileImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "column_table0", length = 1000)
    private String columnTable0;

    @Column(name = "column_table1", length = 1000)
    private String columnTable1;

    @Column(name = "column_table2", length = 1000)
    private String columnTable2;

    @Column(name = "column_table3", length = 1000)
    private String columnTable3;

    @Column(name = "column_table4", length = 1000)
    private String columnTable4;

    @Column(name = "column_table5", length = 1000)
    private String columnTable5;

    @Column(name = "column_table6", length = 1000)
    private String columnTable6;

    @Column(name = "column_table7", length = 1000)
    private String columnTable7;

    @Column(name = "column_table8", length = 1000)
    private String columnTable8;

    @Column(name = "column_table9", length = 1000)
    private String columnTable9;

    @Column(name = "column_table10", length = 1000)
    private String columnTable10;

    @Column(name = "column_table11", length = 1000)
    private String columnTable11;

    @Column(name = "column_table12", length = 1000)
    private String columnTable12;

    @Column(name = "column_table13", length = 1000)
    private String columnTable13;

    @Column(name = "column_table14", length = 1000)
    private String columnTable14;

    @Column(name = "column_table15", length = 1000)
    private String columnTable15;

    @Column(name = "column_table16", length = 1000)
    private String columnTable16;

    @Column(name = "column_table17", length = 1000)
    private String columnTable17;

    @Column(name = "column_table18", length = 1000)
    private String columnTable18;

    @Column(name = "column_table19", length = 1000)
    private String columnTable19;

    @Column(name = "column_table20", length = 1000)
    private String columnTable20;

    @Column(name = "column_table21", length = 1000)
    private String columnTable21;

    @Column(name = "column_table22", length = 1000)
    private String columnTable22;

    @Column(name = "column_table23", length = 1000)
    private String columnTable23;

    @Column(name = "column_table24", length = 1000)
    private String columnTable24;

    @Column(name = "column_table25", length = 1000)
    private String columnTable25;

    @Column(name = "column_table26", length = 1000)
    private String columnTable26;

    @Column(name = "column_table27", length = 1000)
    private String columnTable27;

    @Column(name = "column_table28", length = 1000)
    private String columnTable28;

    @Column(name = "column_table29", length = 1000)
    private String columnTable29;

    @Column(name = "column_table30", length = 1000)
    private String columnTable30;

    @Column(name = "column_table31", length = 1000)
    private String columnTable31;

    @Column(name = "column_table32", length = 1000)
    private String columnTable32;

    @Column(name = "column_table33", length = 1000)
    private String columnTable33;

    @Column(name = "column_table34", length = 1000)
    private String columnTable34;

    @Column(name = "column_table35", length = 1000)
    private String columnTable35;

    @Column(name = "column_table36", length = 1000)
    private String columnTable36;

    @Column(name = "column_table37", length = 1000)
    private String columnTable37;

    @Column(name = "column_table38", length = 1000)
    private String columnTable38;

    @Column(name = "column_table39", length = 1000)
    private String columnTable39;

    @Column(name = "column_table40", length = 1000)
    private String columnTable40;

    @Column(name = "operacion_proceso")
    @NotNull
    private String operacionProceso;

    @Column(name = "tipo_operacion_proceso")
    @NotNull
    private String tipoOperacion;

    @Column(name = "tipo_entidad")
    @NotNull
    private String tipoEntidad;

    @Column(name = "archivo_importacion")
    @NotNull
    private String archivoImportacion;

    public FileImport() {

    }
}
