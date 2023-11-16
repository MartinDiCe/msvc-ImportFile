package com.diceprojects.importfile.services;

import com.diceprojects.importfile.clients.ColumnsClientRestApi;
import com.diceprojects.importfile.persistences.models.FileColumnsHeader;
import com.diceprojects.importfile.persistences.models.dto.ImportResponseDTO;
import com.diceprojects.importfile.utils.FileExtensionHandler;
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

import com.diceprojects.importfile.exceptions.ColumnsNoEncontradasException;
import com.diceprojects.importfile.persistences.models.entities.FileImport;
import com.diceprojects.importfile.persistences.repositories.FileImportRepository;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la importación de archivos en msvc-FileImport.
 */
@Service
public class FileImportServiceImplement implements FileImportService {

    private final FileImportRepository repository;
    private final ColumnsClientRestApi columnClient;
    private final ObjectMapper objectMapper;

    public FileImportServiceImplement(FileImportRepository repository, ColumnsClientRestApi columnClient, ObjectMapper objectMapper) {
        this.repository = repository;
        this.columnClient = columnClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Importa un archivo a la base de datos.
     *
     * @param filePath Ruta del archivo.
     * @param fileName Nombre del archivo.
     * @return Una instancia de {@link ImportResponseDTO} opcional que contiene detalles sobre la importación.
     * @throws ColumnsNoEncontradasException Si no se encuentran las columnas correspondientes al archivo.
     * @throws ResponseStatusException       Si el archivo ya ha sido importado anteriormente.
     * @throws ResponseStatusException       Si se produce un error durante la importación del archivo.
     * @throws ResponseStatusException       Si hay un error al obtener registros o al intentar conectarse con el microservicio msvc-columns.
     */
    @Override
    public Optional<ImportResponseDTO> importFile(String filePath, String fileName) {

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

            String file = FileExtensionHandler.handleFileExtension(filePath,fileName,fileColumns.getDelimitadorArchivoMapping());

            List<String[]> rows = readCSVFile(file, fileColumns.getDelimitadorArchivoMapping());

            int processedRows = 0;

            try {
                Method[] fileImportMethods = FileImport.class.getMethods();

                for (String[] row : rows) {
                    if (fileColumns.getIgnoreFirstRowMapping() && processedRows == 0) {
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

        } catch (FeignException e) {

            throw new ResponseStatusException(HttpStatus.
                    INTERNAL_SERVER_ERROR, "Error al obtener registros o  intentar conectarse con el microservicio msvc-columns " + e.getMessage());

        }
    }

    /**
     * Lee el contenido de un archivo CSV y devuelve una lista de matrices de cadenas.
     *
     * @param file                      Ruta del archivo CSV.
     * @param delimitadorArchivoMapping Delimitador del archivo mapeado.
     * @return Una lista de matrices de cadenas que representan las filas y columnas del archivo CSV.
     * @throws ResponseStatusException Si hay un error al leer el contenido del archivo o al leer el archivo CSV.
     */
    public List<String[]> readCSVFile(String file, String delimitadorArchivoMapping) {
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

    /**
     * Obtiene el valor de una columna en una fila dada el índice de la columna.
     *
     * @param row         Matriz de cadenas que representa una fila.
     * @param columnIndex Índice de la columna de la cual obtener el valor.
     * @return El valor de la columna en la fila.
     * @throws ResponseStatusException Si el índice de la columna está fuera de los límites de la fila o si hay un error al leer las columnas de la fila.
     */
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

    /**
     * Busca un método por su nombre en la lista de métodos.
     *
     * @param methods Lista de métodos.
     * @param name    Nombre del método a buscar.
     * @return El método encontrado o null si no se encuentra.
     */
    private Method findMethodByName(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Obtiene la configuración de columnas para un archivo dado.
     *
     * @param fileName Nombre del archivo para el cual se desea obtener la configuración de columnas.
     * @return La configuración de columnas correspondiente al archivo.
     * @throws ResponseStatusException Si el nombre de archivo no coincide con ninguna configuración de importación o si hay un error al conectarse con el microservicio msvc-columns.
     */
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
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.
                    BAD_REQUEST, "Error al intentar conectarse con la el microservicio msvc-columns" + e.getMessage());
        }
    }

}
