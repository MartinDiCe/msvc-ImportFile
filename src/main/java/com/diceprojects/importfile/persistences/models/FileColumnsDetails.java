package com.diceprojects.importfile.persistences.models;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Clase que representa los detalles de las columnas de un archivo.
 */
@Data
public class FileColumnsDetails {

    private Long id;
    private Integer column0Mapping;
    private String nameColumn0;
    private Integer column1Mapping;
    private String nameColumn1;
    private Integer column2Mapping;
    private String nameColumn2;
    private Integer column3Mapping;
    private String nameColumn3;
    private Integer column4Mapping;
    private String nameColumn4;
    private Integer column5Mapping;
    private String nameColumn5;
    private Integer column6Mapping;
    private String nameColumn6;
    private Integer column7Mapping;
    private String nameColumn7;
    private Integer column8Mapping;
    private String nameColumn8;
    private Integer column9Mapping;
    private String nameColumn9;
    private Integer column10Mapping;
    private String nameColumn10;
    private Integer column11Mapping;
    private String nameColumn11;
    private Integer column12Mapping;
    private String nameColumn12;
    private Integer column13Mapping;
    private String nameColumn13;
    private Integer column14Mapping;
    private String nameColumn14;
    private Integer column15Mapping;
    private String nameColumn15;
    private Integer column16Mapping;
    private String nameColumn16;
    private Integer column17Mapping;
    private String nameColumn17;
    private Integer column18Mapping;
    private String nameColumn18;
    private Integer column19Mapping;
    private String nameColumn19;
    private Integer column20Mapping;
    private String nameColumn20;
    private Integer column21Mapping;
    private String nameColumn21;
    private Integer column22Mapping;
    private String nameColumn22;
    private Integer column23Mapping;
    private String nameColumn23;
    private Integer column24Mapping;
    private String nameColumn24;
    private Integer column25Mapping;
    private String nameColumn25;
    private Integer column26Mapping;
    private String nameColumn26;
    private Integer column27Mapping;
    private String nameColumn27;
    private Integer column28Mapping;
    private String nameColumn28;
    private Integer column29Mapping;
    private String nameColumn29;
    private Integer column30Mapping;
    private String nameColumn30;
    private Integer column31Mapping;
    private String nameColumn31;
    private Integer column32Mapping;
    private String nameColumn32;
    private Integer column33Mapping;
    private String nameColumn33;
    private Integer column34Mapping;
    private String nameColumn34;
    private Integer column35Mapping;
    private String nameColumn35;
    private Integer column36Mapping;
    private String nameColumn36;
    private Integer column37Mapping;
    private String nameColumn37;
    private Integer column38Mapping;
    private String nameColumn38;
    private Integer column39Mapping;
    private String nameColumn39;
    private Integer column40Mapping;
    private String nameColumn40;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public FileColumnsDetails() {
    }

    public Integer getColumnXMapping(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return getColumn0Mapping();
            case 1:
                return getColumn1Mapping();
            case 2:
                return getColumn2Mapping();
            case 3:
                return getColumn3Mapping();
            case 4:
                return getColumn4Mapping();
            case 5:
                return getColumn5Mapping();
            case 6:
                return getColumn6Mapping();
            case 7:
                return getColumn7Mapping();
            case 8:
                return getColumn8Mapping();
            case 9:
                return getColumn9Mapping();
            case 10:
                return getColumn10Mapping();
            case 11:
                return getColumn11Mapping();
            case 12:
                return getColumn12Mapping();
            case 13:
                return getColumn13Mapping();
            case 14:
                return getColumn14Mapping();
            case 15:
                return getColumn15Mapping();
            case 16:
                return getColumn16Mapping();
            case 17:
                return getColumn17Mapping();
            case 18:
                return getColumn18Mapping();
            case 19:
                return getColumn19Mapping();
            case 20:
                return getColumn20Mapping();
            case 21:
                return getColumn21Mapping();
            case 22:
                return getColumn22Mapping();
            case 23:
                return getColumn23Mapping();
            case 24:
                return getColumn24Mapping();
            case 25:
                return getColumn25Mapping();
            case 26:
                return getColumn26Mapping();
            case 27:
                return getColumn27Mapping();
            case 28:
                return getColumn28Mapping();
            case 29:
                return getColumn29Mapping();
            case 30:
                return getColumn30Mapping();
            case 31:
                return getColumn31Mapping();
            case 32:
                return getColumn32Mapping();
            case 33:
                return getColumn33Mapping();
            case 34:
                return getColumn34Mapping();
            case 35:
                return getColumn35Mapping();
            case 36:
                return getColumn36Mapping();
            case 37:
                return getColumn37Mapping();
            case 38:
                return getColumn38Mapping();
            case 39:
                return getColumn39Mapping();
            case 40:
                return getColumn40Mapping();
            default:
                return null;
        }
    }

}