package com.diceprojects.importfile.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;

import static com.diceprojects.importfile.utils.ExcelToCsvConverter.convertExcelToCsv;

/**
 * Clase que maneja la extensión de archivos y realiza la conversión de XLS y XLSX a CSV.
 */
public class FileExtensionHandler {

    /**
     * Maneja la extensión de archivos y realiza la conversión de XLS y XLSX a CSV.
     *
     * @param filePath    Ruta del archivo.
     * @param fileName    Nombre del archivo.
     * @param delimitador Delimitador a utilizar en el archivo CSV.
     * @return Ruta del archivo CSV generado.
     * @throws ResponseStatusException Si hay algún error durante la manipulación del archivo.
     */
    public static String handleFileExtension(String filePath, String fileName, String delimitador) {
        String lowerCaseFileName = fileName.toLowerCase();

        try {
            if (lowerCaseFileName.endsWith(".xls") || lowerCaseFileName.endsWith(".xlsx")) {
                return convertExcelToCsv(filePath, fileName, delimitador);
            } else if (lowerCaseFileName.endsWith(".csv")) {
                return filePath + fileName;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Extensión de archivo no admitida. Solo se admiten archivos CSV, XLS y XLSX.");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al convertir el archivo Excel a CSV", e);
        }
    }
}