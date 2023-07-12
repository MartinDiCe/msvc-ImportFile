package com.diceprojects.importcsvmeli.persistences.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "columnas_Mapper")
public class Columnas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "columna0")
    private Integer columna0Mapping;

    @Column(name = "columna1")
    private Integer columna1Mapping;

    @Column(name = "columna2")
    private Integer columna2Mapping;

    @Column(name = "columna3")
    private Integer columna3Mapping;

    @Column(name = "columna4")
    private Integer columna4Mapping;

    @Column(name = "columna5")
    private Integer columna5Mapping;

    @Column(name = "columna6")
    private Integer columna6Mapping;

    @Column(name = "columna7")
    private Integer columna7Mapping;

    @Column(name = "columna8")
    private Integer columna8Mapping;

    @Column(name = "columna9")
    private Integer columna9Mapping;

    @Column(name = "columna10")
    private Integer columna10Mapping;

    @Column(name = "columna11")
    private Integer columna11Mapping;

    @Column(name = "columna12")
    private Integer columna12Mapping;

    @Column(name = "columna13")
    private Integer columna13Mapping;

    @Column(name = "columna14")
    private Integer columna14Mapping;

    @Column(name = "columna15")
    private Integer columna15Mapping;

    @Column(name = "columna16")
    private Integer columna16Mapping;

    @Column(name = "columna17")
    private Integer columna17Mapping;

    @Column(name = "columna18")
    private Integer columna18Mapping;

    @Column(name = "columna19")
    private Integer columna19Mapping;

    @Column(name = "columna20")
    private Integer columna20Mapping;

    @Column(name = "columna21")
    private Integer columna21Mapping;

    @Column(name = "columna22")
    private Integer columna22Mapping;

    @Column(name = "columna23")
    private Integer columna23Mapping;

    @Column(name = "columna24")
    private Integer columna24Mapping;

    @Column(name = "columna25")
    private Integer columna25Mapping;

    @Column(name = "columna26")
    private Integer columna26Mapping;

    @Column(name = "columna27")
    private Integer columna27Mapping;

    @Column(name = "columna28")
    private Integer columna28Mapping;

    @Column(name = "columna29")
    private Integer columna29Mapping;

    @Column(name = "columna30")
    private Integer columna30Mapping;

    @Column(name = "columna31")
    private Integer columna31Mapping;

    @Column(name = "columna32")
    private Integer columna32Mapping;

    @Column(name = "columna33")
    private Integer columna33Mapping;

    @Column(name = "columna34")
    private Integer columna34Mapping;

    @Column(name = "columna35")
    private Integer columna35Mapping;

    @Column(name = "columna36")
    private Integer columna36Mapping;

    @Column(name = "columna37")
    private Integer columna37Mapping;

    @Column(name = "columna38")
    private Integer columna38Mapping;

    @Column(name = "columna39")
    private Integer columna39Mapping;

    @Column(name = "columna40")
    private Integer columna40Mapping;

    @Column(name = "delimitador_archivo")
    private char delimitadorArchivoMapping;

    @Column(name = "operacion_referencia")
    private String operacionMapping;

    @Column(name = "inicio_archivo", length = 3)
    private String inicioArchivoMapping;

    @Column(name = "tipo_operacion_proceso", length = 3)
    private String tipoOperacionProcesoMapping;


    public Columnas(Long id, Integer columna0Mapping, Integer columna1Mapping, Integer columna2Mapping, Integer columna3Mapping, Integer columna4Mapping, Integer columna5Mapping, Integer columna6Mapping, Integer columna7Mapping, Integer columna8Mapping, Integer columna9Mapping, Integer columna10Mapping, Integer columna11Mapping, Integer columna12Mapping, Integer columna13Mapping, Integer columna14Mapping, Integer columna15Mapping, Integer columna16Mapping, Integer columna17Mapping, Integer columna18Mapping, Integer columna19Mapping, Integer columna20Mapping, Integer columna21Mapping, Integer columna22Mapping, Integer columna23Mapping, Integer columna24Mapping, Integer columna25Mapping, Integer columna26Mapping, Integer columna27Mapping, Integer columna28Mapping, Integer columna29Mapping, Integer columna30Mapping, Integer columna31Mapping, Integer columna32Mapping, Integer columna33Mapping, Integer columna34Mapping, Integer columna35Mapping, Integer columna36Mapping, Integer columna37Mapping, Integer columna38Mapping, Integer columna39Mapping, Integer columna40Mapping ,String operacionMapping, char delimitadorArchivo, String inicioArchivoMapping, String tipoOperacionProcesoMapping) {
        this.id = id;
        this.columna0Mapping = columna0Mapping;
        this.columna1Mapping = columna1Mapping;
        this.columna2Mapping = columna2Mapping;
        this.columna3Mapping = columna3Mapping;
        this.columna4Mapping = columna4Mapping;
        this.columna5Mapping = columna5Mapping;
        this.columna6Mapping = columna6Mapping;
        this.columna7Mapping = columna7Mapping;
        this.columna8Mapping = columna8Mapping;
        this.columna9Mapping = columna9Mapping;
        this.columna10Mapping = columna10Mapping;
        this.columna11Mapping = columna11Mapping;
        this.columna12Mapping = columna12Mapping;
        this.columna13Mapping = columna13Mapping;
        this.columna14Mapping = columna14Mapping;
        this.columna15Mapping = columna15Mapping;
        this.columna16Mapping = columna16Mapping;
        this.columna17Mapping = columna17Mapping;
        this.columna18Mapping = columna18Mapping;
        this.columna19Mapping = columna19Mapping;
        this.columna20Mapping = columna20Mapping;
        this.columna21Mapping = columna21Mapping;
        this.columna22Mapping = columna22Mapping;
        this.columna23Mapping = columna23Mapping;
        this.columna24Mapping = columna24Mapping;
        this.columna25Mapping = columna25Mapping;
        this.columna26Mapping = columna26Mapping;
        this.columna27Mapping = columna27Mapping;
        this.columna28Mapping = columna28Mapping;
        this.columna29Mapping = columna29Mapping;
        this.columna30Mapping = columna30Mapping;
        this.columna31Mapping = columna31Mapping;
        this.columna32Mapping = columna32Mapping;
        this.columna33Mapping = columna33Mapping;
        this.columna34Mapping = columna34Mapping;
        this.columna35Mapping = columna35Mapping;
        this.columna36Mapping = columna36Mapping;
        this.columna37Mapping = columna37Mapping;
        this.columna38Mapping = columna38Mapping;
        this.columna39Mapping = columna39Mapping;
        this.columna40Mapping = columna40Mapping;
        this.delimitadorArchivoMapping = delimitadorArchivo;
        this.operacionMapping = operacionMapping;
        this.tipoOperacionProcesoMapping = tipoOperacionProcesoMapping;
        this.inicioArchivoMapping = inicioArchivoMapping;
    }

    public Columnas() {

    }
}
