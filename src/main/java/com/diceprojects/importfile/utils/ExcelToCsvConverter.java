package com.diceprojects.importfile.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Utilidad para convertir archivos Excel (.xls, .xlsx) a formato CSV.
 */
public class ExcelToCsvConverter {

    /**
     * Convierte un archivo Excel a formato CSV.
     *
     * @param filePath    Ruta del archivo.
     * @param fileName    Nombre del archivo.
     * @param delimitador Delimitador para el archivo CSV.
     * @return Ruta del archivo CSV creado.
     * @throws IOException Si hay un error de lectura o escritura.
     */
    public static String convertExcelToCsv(String filePath, String fileName, String delimitador) throws IOException {
        try {
            Workbook workbook = getWorkbook(filePath, fileName);
            Sheet sheet = workbook.getSheetAt(0);

            StringBuilder csvContent = new StringBuilder();

            for (Row row : sheet) {
                List<String> rowData = getRowData(row);
                Iterator<String> rowDataIterator = rowData.iterator();

                while (rowDataIterator.hasNext()) {
                    csvContent.append(rowDataIterator.next());

                    if (rowDataIterator.hasNext()) {
                        csvContent.append(delimitador);
                    }
                }

                csvContent.append(System.lineSeparator());
            }

            return saveCsvFile(filePath, fileName, csvContent.toString());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al leer el archivo Excel", e);
        }
    }

    /**
     * Obtiene el libro de trabajo (Workbook) basado en la extensión del archivo.
     *
     * @param filePath Ruta del archivo.
     * @param fileName Nombre del archivo.
     * @return Libro de trabajo (Workbook).
     * @throws IOException Si hay un error de lectura o si el archivo no es un archivo Excel válido.
     */
    private static Workbook getWorkbook(String filePath, String fileName) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(filePath + fileName))) {
            if (fileName.endsWith(".xls")) {
                return new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                return new XSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("El archivo no es un archivo Excel válido (.xls o .xlsx)");
            }
        }
    }

    /**
     * Obtiene el valor de una celda como cadena.
     *
     * @param cell Celda.
     * @return Valor de la celda como cadena.
     */
    private static String getCellValue(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        return new SimpleDateFormat("M/d/yyyy").format(date);
                    } else {
                        double numericValue = cell.getNumericCellValue();
                        return (Math.abs(numericValue) > 100000) ? String.format("%.0f", numericValue) : String.valueOf(numericValue);
                    }
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return "";
            }
        }
    }

    /**
     * Obtiene los datos de una fila como una lista de cadenas.
     *
     * @param row Fila.
     * @return Lista de datos de la fila.
     */
    private static List<String> getRowData(Row row) {
        List<String> rowData = new ArrayList<>();
        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            rowData.add(getCellValue(cell));
        }

        return rowData;
    }

    /**
     * Guarda el contenido CSV en un archivo y devuelve la ruta del archivo CSV creado.
     *
     * @param filePath     Ruta del archivo.
     * @param fileName     Nombre del archivo Excel.
     * @param csvContent   Contenido CSV.
     * @return Ruta del archivo CSV creado.
     * @throws IOException Si hay un error de escritura.
     */
    private static String saveCsvFile(String filePath, String fileName, String csvContent) throws IOException {
        String csvFileName = fileName.replaceFirst("[.][^.]+$", "") + ".csv";
        String csvFilePath = filePath + csvFileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write(csvContent);
        }

        return csvFilePath;
    }
}
