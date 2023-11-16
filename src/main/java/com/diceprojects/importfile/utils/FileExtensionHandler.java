package com.diceprojects.importfile.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;

import static com.diceprojects.importfile.utils.ExcelToCsvConverter.convertExcelToCsv;

public class FileExtensionHandler {

    public static String handleFileExtension(String filePath, String fileName, String delimitador) {

        String lowerCaseFileName = fileName.toLowerCase();

        if (lowerCaseFileName.endsWith(".xls") || lowerCaseFileName.endsWith(".xlsx")) {
            try {
                return convertExcelToCsv(filePath, fileName, delimitador);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al convertir el archivo Excel a CSV", e);
            }
        } else if (lowerCaseFileName.endsWith(".csv")) {
            return filePath + fileName;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Extensión de archivo no admitida. Solo se admiten archivos CSV, XLS y XLSX.");
        }
    }

}
