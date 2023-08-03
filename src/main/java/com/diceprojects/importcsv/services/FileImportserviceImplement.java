package com.diceprojects.importcsv.services;

import com.diceprojects.importcsv.clients.ColumnsClientRestApi;
import com.diceprojects.importcsv.persistences.models.FileColumnsHeader;
import com.diceprojects.importcsv.persistences.models.dto.ImportResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import com.diceprojects.importcsv.exceptions.ColumnsNoEncontradasException;
import com.diceprojects.importcsv.persistences.models.entities.FileImport;
import com.diceprojects.importcsv.persistences.repositories.FileImportRepository;
import org.springframework.stereotype.Service;


@Service
public class FileImportserviceImplement implements FileImportservice {

    private final FileImportRepository repository;
    private final ColumnsClientRestApi columnClient;
    private final ObjectMapper objectMapper;

    public FileImportserviceImplement(FileImportRepository repository, ColumnsClientRestApi columnClient, ObjectMapper objectMapper) {
        this.repository = repository;
        this.columnClient = columnClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<ImportResponseDTO> importFile(String filePath, String fileName) {

        String file = new StringBuilder()
                .append(filePath)
                .append(fileName)
                .toString();

        try {
            FileColumnsHeader fileColumns = columnClient.getConfigColumnFromFileName(fileName);

            if (fileColumns == null) {
                throw new ColumnsNoEncontradasException(fileName);
            }

            FileImport existingImport = repository.findFirstByArchivoImportacion(fileName);
            if (existingImport != null) {
                throw new ResponseStatusException(HttpStatus.
                        CONFLICT, "El archivo '" + fileName + "' ya ha sido importado anteriormente.");
            }

            List<String[]> rows = readCSVFile(file, fileColumns.getDelimitadorArchivoMapping());

            int processedRows = 0;

            try {
                Method[] fileImportMethods = FileImport.class.getMethods();

                for (String[] row : rows) {
                    if (processedRows == 0) {
                        processedRows++;
                        continue;
                    }

                    FileImport fileImport = new FileImport();

                    for (int columnIndex = 0; columnIndex < fileImportMethods.length; columnIndex++) {
                        String methodName = "setColumnTable" + columnIndex;
                        Method method = findMethodByName(fileImportMethods, methodName);

                        if (method != null) {
                            Integer columnMapping = fileColumns.getFileColumnsDetails().getColumnXMapping(columnIndex);
                            if (columnMapping != null) {
                                String columnValue = getValueFromMapping(row, columnMapping);
                                if (columnValue != null && columnValue.length() > 1000) {
                                    columnValue = columnValue.substring(0, 1000);
                                }
                                method.invoke(fileImport, columnValue);
                            }
                        }

                        fileImport.setColumnsId(fileColumns.getId());
                        fileImport.setArchivoImportacion(fileName);
                    }

                        repository.save(fileImport);

                        processedRows++;

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
            catch (FeignException e) {

                throw new ResponseStatusException(HttpStatus.
                        INTERNAL_SERVER_ERROR, "Error al obtener registros o  intentar conectarse con el microservicio msvc-columns " + e.getMessage());

        }
    }

        public List<String[]> readCSVFile (String file, String delimitadorArchivoMapping){
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

    private Method findMethodByName(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    public FileColumnsHeader getConfigColumnFromFileName(String fileName) {

        try {
            List<FileColumnsHeader> response = columnClient.listAllColumns();
            List<FileColumnsHeader> allColumns = response;

            if (allColumns != null) {
                for (FileColumnsHeader column : allColumns) {
                    if (fileName.toLowerCase()
                            .startsWith(column.getStartFile()
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
