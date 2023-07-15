package com.diceprojects.importcsv.services;

import com.diceprojects.importcsv.clients.ColumnsClientRestApi;
import com.diceprojects.importcsv.persistences.models.dto.ImportResponseDTO;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.diceprojects.importcsv.exceptions.ColumnsNoEncontradasException;
import com.diceprojects.importcsv.persistences.models.Columns;
import com.diceprojects.importcsv.persistences.models.entities.FileImport;
import com.diceprojects.importcsv.persistences.repositories.FileImportRepository;
import org.springframework.stereotype.Service;


@Service
public class FileImportserviceImplement implements FileImportservice {

    private final FileImportRepository repository;
    private final ColumnsClientRestApi columnClient;

    public FileImportserviceImplement(FileImportRepository repository, ColumnsClientRestApi columnClient) {
        this.repository = repository;
        this.columnClient = columnClient;
    }

    @Override
    public Optional<ImportResponseDTO> importFile(String filePath, String fileName) {

        String file = new StringBuilder()
                .append(filePath)
                .append(fileName)
                .toString();

        try {
            Columns columns = columnClient.getConfigColumnFromFileName(fileName).getBody();

            if (columns == null) {
                throw new ColumnsNoEncontradasException(fileName);
            }

            FileImport existingImport = repository.findFirstByArchivoImportacion(fileName);
            if (existingImport != null) {
                throw new ResponseStatusException(HttpStatus.
                        CONFLICT, "El archivo '" + fileName + "' ya ha sido importado anteriormente.");
            }

            List<String[]> rows = readCSVFile(file, columns.getDelimitadorArchivoMapping());
            int processedRows = 0;

            try {
                boolean firstRowSkipped = false;
                for (String[] row : rows) {
                    if (!firstRowSkipped) {
                        firstRowSkipped = true;
                        continue;
                    }

                    try {
                        FileImport fileImport = new FileImport();

                        if (columns.getColumn0Mapping() != null) {
                            String column0Str = getValueFromMapping(row, columns.getColumn0Mapping());
                            if (column0Str != null) {
                                if (column0Str.length() > 1000) {
                                    column0Str = column0Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable0(column0Str);
                            }
                        }

                        if (columns.getColumn1Mapping() != null) {
                            String column1Str = getValueFromMapping(row, columns.getColumn1Mapping());
                            if (column1Str != null) {
                                if (column1Str.length() > 1000) {
                                    column1Str = column1Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable1(column1Str);
                            }
                        }

                        if (columns.getColumn2Mapping() != null) {
                            String column2Str = getValueFromMapping(row, columns.getColumn2Mapping());
                            if (column2Str != null) {
                                if (column2Str.length() > 1000) {
                                    column2Str = column2Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable2(column2Str);
                            }
                        }

                        if (columns.getColumn3Mapping() != null) {
                            String column3Str = getValueFromMapping(row, columns.getColumn3Mapping());
                            if (column3Str != null) {
                                if (column3Str.length() > 1000) {
                                    column3Str = column3Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable3(column3Str);
                            }
                        }

                        if (columns.getColumn4Mapping() != null) {
                            String column4Str = getValueFromMapping(row, columns.getColumn4Mapping());
                            if (column4Str != null) {
                                if (column4Str.length() > 1000) {
                                    column4Str = column4Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable4(column4Str);
                            }
                        }

                        if (columns.getColumn5Mapping() != null) {
                            String column5Str = getValueFromMapping(row, columns.getColumn5Mapping());
                            if (column5Str != null) {
                                if (column5Str.length() > 1000) {
                                    column5Str = column5Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable5(column5Str);
                            }
                        }

                        if (columns.getColumn6Mapping() != null) {
                            String column6Str = getValueFromMapping(row, columns.getColumn6Mapping());
                            if (column6Str != null) {
                                if (column6Str.length() > 1000) {
                                    column6Str = column6Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable22(column6Str);
                            }
                        }

                        if (columns.getColumn7Mapping() != null) {
                            String column7Str = getValueFromMapping(row, columns.getColumn7Mapping());
                            if (column7Str != null) {
                                if (column7Str.length() > 1000) {
                                    column7Str = column7Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable7(column7Str);
                            }
                        }

                        if (columns.getColumn8Mapping() != null) {
                            String column8Str = getValueFromMapping(row, columns.getColumn8Mapping());
                            if (column8Str != null) {
                                if (column8Str.length() > 1000) {
                                    column8Str = column8Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable8(column8Str);
                            }
                        }

                        if (columns.getColumn9Mapping() != null) {
                            String column9Str = getValueFromMapping(row, columns.getColumn9Mapping());
                            if (column9Str != null) {
                                if (column9Str.length() > 1000) {
                                    column9Str = column9Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable9(column9Str);
                            }
                        }

                        if (columns.getColumn10Mapping() != null) {
                            String column10Str = getValueFromMapping(row, columns.getColumn10Mapping());
                            if (column10Str != null) {
                                if (column10Str.length() > 1000) {
                                    column10Str = column10Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable10(column10Str);
                            }
                        }

                        if (columns.getColumn11Mapping() != null) {
                            String column11Str = getValueFromMapping(row, columns.getColumn11Mapping());
                            if (column11Str != null) {
                                if (column11Str.length() > 1000) {
                                    column11Str = column11Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable11(column11Str);
                            }
                        }

                        if (columns.getColumn12Mapping() != null) {
                            String column12Str = getValueFromMapping(row, columns.getColumn12Mapping());
                            if (column12Str != null) {
                                if (column12Str.length() > 1000) {
                                    column12Str = column12Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable12(column12Str);
                            }
                        }

                        if (columns.getColumn13Mapping() != null) {
                            String column13Str = getValueFromMapping(row, columns.getColumn13Mapping());
                            if (column13Str != null) {
                                if (column13Str.length() > 1000) {
                                    column13Str = column13Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable13(column13Str);
                            }
                        }

                        if (columns.getColumn14Mapping() != null) {
                            String column14Str = getValueFromMapping(row, columns.getColumn14Mapping());
                            if (column14Str != null) {
                                if (column14Str.length() > 1000) {
                                    column14Str = column14Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable14(column14Str);
                            }
                        }

                        if (columns.getColumn15Mapping() != null) {
                            String column15Str = getValueFromMapping(row, columns.getColumn15Mapping());
                            if (column15Str != null) {
                                if (column15Str.length() > 1000) {
                                    column15Str = column15Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable15(column15Str);
                            }
                        }

                        if (columns.getColumn16Mapping() != null) {
                            String column16Str = getValueFromMapping(row, columns.getColumn16Mapping());
                            if (column16Str != null) {
                                if (column16Str.length() > 1000) {
                                    column16Str = column16Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable16(column16Str);
                            }
                        }

                        if (columns.getColumn17Mapping() != null) {
                            String column17Str = getValueFromMapping(row, columns.getColumn17Mapping());
                            if (column17Str != null) {
                                if (column17Str.length() > 1000) {
                                    column17Str = column17Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable17(column17Str);
                            }
                        }

                        if (columns.getColumn18Mapping() != null) {
                            String column18Str = getValueFromMapping(row, columns.getColumn18Mapping());
                            if (column18Str != null) {
                                if (column18Str.length() > 1000) {
                                    column18Str = column18Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable18(column18Str);
                            }
                        }

                        if (columns.getColumn19Mapping() != null) {
                            String column19Str = getValueFromMapping(row, columns.getColumn19Mapping());
                            if (column19Str != null) {
                                if (column19Str.length() > 1000) {
                                    column19Str = column19Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable19(column19Str);
                            }
                        }

                        if (columns.getColumn20Mapping() != null) {
                            String column20Str = getValueFromMapping(row, columns.getColumn20Mapping());
                            if (column20Str != null) {
                                if (column20Str.length() > 1000) {
                                    column20Str = column20Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable20(column20Str);
                            }
                        }

                        if (columns.getColumn21Mapping() != null) {
                            String column21Str = getValueFromMapping(row, columns.getColumn21Mapping());
                            if (column21Str != null) {
                                if (column21Str.length() > 1000) {
                                    column21Str = column21Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable21(column21Str);
                            }
                        }

                        if (columns.getColumn22Mapping() != null) {
                            String column22Str = getValueFromMapping(row, columns.getColumn22Mapping());
                            if (column22Str != null) {
                                if (column22Str.length() > 1000) {
                                    column22Str = column22Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable22(column22Str);
                            }
                        }

                        if (columns.getColumn23Mapping() != null) {
                            String column23Str = getValueFromMapping(row, columns.getColumn23Mapping());
                            if (column23Str != null) {
                                if (column23Str.length() > 1000) {
                                    column23Str = column23Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable23(column23Str);
                            }
                        }

                        if (columns.getColumn24Mapping() != null) {
                            String column24Str = getValueFromMapping(row, columns.getColumn24Mapping());
                            if (column24Str != null) {
                                if (column24Str.length() > 1000) {
                                    column24Str = column24Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable24(column24Str);
                            }
                        }

                        if (columns.getColumn25Mapping() != null) {
                            String column25Str = getValueFromMapping(row, columns.getColumn25Mapping());
                            if (column25Str != null) {
                                if (column25Str.length() > 1000) {
                                    column25Str = column25Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable25(column25Str);
                            }
                        }

                        if (columns.getColumn26Mapping() != null) {
                            String column26Str = getValueFromMapping(row, columns.getColumn26Mapping());
                            if (column26Str != null) {
                                if (column26Str.length() > 1000) {
                                    column26Str = column26Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable26(column26Str);
                            }
                        }

                        if (columns.getColumn27Mapping() != null) {
                            String column27Str = getValueFromMapping(row, columns.getColumn27Mapping());
                            if (column27Str != null) {
                                if (column27Str.length() > 1000) {
                                    column27Str = column27Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable27(column27Str);
                            }
                        }

                        if (columns.getColumn28Mapping() != null) {
                            String column28Str = getValueFromMapping(row, columns.getColumn28Mapping());
                            if (column28Str != null) {
                                if (column28Str.length() > 1000) {
                                    column28Str = column28Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable28(column28Str);
                            }
                        }

                        if (columns.getColumn29Mapping() != null) {
                            String column29Str = getValueFromMapping(row, columns.getColumn29Mapping());
                            if (column29Str != null) {
                                if (column29Str.length() > 1000) {
                                    column29Str = column29Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable29(column29Str);
                            }
                        }

                        if (columns.getColumn30Mapping() != null) {
                            String column30Str = getValueFromMapping(row, columns.getColumn30Mapping());
                            if (column30Str != null) {
                                if (column30Str.length() > 1000) {
                                    column30Str = column30Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable30(column30Str);
                            }
                        }

                        if (columns.getColumn31Mapping() != null) {
                            String column31Str = getValueFromMapping(row, columns.getColumn31Mapping());
                            if (column31Str != null) {
                                if (column31Str.length() > 1000) {
                                    column31Str = column31Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable31(column31Str);
                            }
                        }

                        if (columns.getColumn32Mapping() != null) {
                            String column32Str = getValueFromMapping(row, columns.getColumn32Mapping());
                            if (column32Str != null) {
                                if (column32Str.length() > 1000) {
                                    column32Str = column32Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable32(column32Str);
                            }
                        }

                        if (columns.getColumn33Mapping() != null) {
                            String column33Str = getValueFromMapping(row, columns.getColumn33Mapping());
                            if (column33Str != null) {
                                if (column33Str.length() > 1000) {
                                    column33Str = column33Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable33(column33Str);
                            }
                        }

                        if (columns.getColumn34Mapping() != null) {
                            String column34Str = getValueFromMapping(row, columns.getColumn34Mapping());
                            if (column34Str != null) {
                                if (column34Str.length() > 1000) {
                                    column34Str = column34Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable34(column34Str);
                            }
                        }

                        if (columns.getColumn35Mapping() != null) {
                            String column35Str = getValueFromMapping(row, columns.getColumn35Mapping());
                            if (column35Str != null) {
                                if (column35Str.length() > 1000) {
                                    column35Str = column35Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable35(column35Str);
                            }
                        }

                        if (columns.getColumn36Mapping() != null) {
                            String column36Str = getValueFromMapping(row, columns.getColumn36Mapping());
                            if (column36Str != null) {
                                if (column36Str.length() > 1000) {
                                    column36Str = column36Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable36(column36Str);
                            }
                        }

                        if (columns.getColumn37Mapping() != null) {
                            String column37Str = getValueFromMapping(row, columns.getColumn37Mapping());
                            if (column37Str != null) {
                                if (column37Str.length() > 1000) {
                                    column37Str = column37Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable37(column37Str);
                            }
                        }

                        if (columns.getColumn38Mapping() != null) {
                            String column38Str = getValueFromMapping(row, columns.getColumn38Mapping());
                            if (column38Str != null) {
                                if (column38Str.length() > 1000) {
                                    column38Str = column38Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable38(column38Str);
                            }
                        }

                        if (columns.getColumn39Mapping() != null) {
                            String column39Str = getValueFromMapping(row, columns.getColumn39Mapping());
                            if (column39Str != null) {
                                if (column39Str.length() > 1000) {
                                    column39Str = column39Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable39(column39Str);
                            }
                        }

                        if (columns.getColumn40Mapping() != null) {
                            String column40Str = getValueFromMapping(row, columns.getColumn40Mapping());
                            if (column40Str != null) {
                                if (column40Str.length() > 1000) {
                                    column40Str = column40Str.substring(0, 1000);
                                }
                                fileImport.setColumnTable40(column40Str);
                            }
                        }

                        fileImport.setColumnsId(columns.getId());
                        fileImport.setArchivoImportacion(fileName);

                        repository.save(fileImport);

                        processedRows++;

                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus
                                .INTERNAL_SERVER_ERROR, "Error al importar archivo", e);
                    }
                }
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus
                        .INTERNAL_SERVER_ERROR, "Error al importar archivo", e);
            }

            ImportResponseDTO response = new ImportResponseDTO();
            response.setStatus("200");
            response.setTitle("Importación exitosa");
            response.setDetail("Cantidad de lineas leídas: " + processedRows);
            return Optional.of(response);

        }
            catch (FeignException e){

            throw new ResponseStatusException(HttpStatus.
                    INTERNAL_SERVER_ERROR, "Error al intentar conectarse con la el microservicio msvc-columns " + e.getMessage());

            }

        }

        public List<String[]> readCSVFile (String file, char delimitadorArchivoMapping){
            List<String[]> rows;
            String delimitador = String.valueOf(delimitadorArchivoMapping);

            try (CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                    .withCSVParser(new CSVParserBuilder().withSeparator(delimitador.charAt(0)).build())
                    .build()) {
                rows = reader.readAll();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.
                        INTERNAL_SERVER_ERROR, "Error al leer el contenido del archivo", e);
            } catch (CsvException e) {
                throw new ResponseStatusException(HttpStatus.
                        INTERNAL_SERVER_ERROR, "Error al leer el contenido del archivo CSV", e);
            }

            return rows;

    }


    public String getValueFromMapping(String[] row, int columnIndex) {

        try {
            if (columnIndex >= 0 && columnIndex < row.length) {
                return row[columnIndex];
            } else {
                throw new ResponseStatusException(HttpStatus
                        .NO_CONTENT, "Las filas y la configuración de lectura de archivo no coinciden");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus
                    .INTERNAL_SERVER_ERROR, "Error al leer las columns de una fila", e);
        }

    }

    public Columns getConfigColumnFromFileName(String fileName) {

        try {
            ResponseEntity<List<Columns>> response = columnClient.listAllColumns();
            List<Columns> allColumns = response.getBody();

            if (allColumns != null) {
                for (Columns column : allColumns) {
                    if (fileName.toLowerCase()
                            .startsWith(column
                                    .getStartFile()
                                    .toLowerCase())) {
                        return column;
                    }
                }
            }

            throw new ResponseStatusException(HttpStatus.
                    BAD_REQUEST, "El nombre de archivo no corresponde a ninguna configuración de importación");
        }
        catch (FeignException e){
            throw new ResponseStatusException(HttpStatus.
                    BAD_REQUEST, "Error al intentar conectarse con la el microservicio msvc-columns" + e.getMessage());
        }
    }

}
