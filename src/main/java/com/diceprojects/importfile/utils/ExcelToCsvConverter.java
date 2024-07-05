package com.diceprojects.importfile.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
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

            CellReference prevCellRef = null;

            for (Row row : sheet) {
                List<String> rowData = getRowData(row);
                Iterator<String> rowDataIterator = rowData.iterator();

                boolean isFirstCell = true;  // Bandera para identificar la primera celda en la fila

                while (rowDataIterator.hasNext()) {
                    CellReference currentCellRef = new CellReference(row.getRowNum(), row.getFirstCellNum() + rowData.indexOf(rowDataIterator.next()));

                    // Verifica si hay un salto en las celdas
                    if (prevCellRef != null && isGapDetected(prevCellRef, currentCellRef)) {
                        // Si hay un salto, agrega celdas vacías hasta alcanzar la celda actual
                        int gapCols = currentCellRef.getCol() - prevCellRef.getCol();
                        for (int i = 0; i < gapCols; i++) {
                            if (!isFirstCell) {
                                csvContent.append(delimitador);
                            }
                            csvContent.append(delimitador);
                        }
                    }

                    // Agrega el valor de la celda actual
                    Cell cell = row.getCell(currentCellRef.getCol());
                    if (!isFirstCell) {
                        csvContent.append(delimitador);
                    }
                    csvContent.append(getCellValue(cell));

                    isFirstCell = false;  // Después de la primera celda, cambia la bandera

                    prevCellRef = currentCellRef;
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
            rowData.add(getCellValueOrEmpty(cell));
        }

        return rowData;
    }

    /**
     * Obtiene el valor de una celda como cadena o una cadena vacía si la celda es nula o está vacía.
     *
     * @param cell Celda.
     * @return Valor de la celda como cadena o cadena vacía si la celda es nula o está vacía.
     */
    private static String getCellValueOrEmpty(Cell cell) {
        return (cell == null || cell.getCellType() == CellType.BLANK) ? "" : getCellValue(cell);
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

    /**
     * Verifica si hay un salto en las columnas o en las filas entre dos referencias de celda consecutivas.
     *
     * @param prevCellRef    La referencia de celda previa.
     * @param currentCellRef La referencia de celda actual.
     * @return true si hay un salto, false de lo contrario.
     */
    private static boolean isGapDetected(CellReference prevCellRef, CellReference currentCellRef) {
        return currentCellRef.getCol() > prevCellRef.getCol() ||
                currentCellRef.getRow() > prevCellRef.getRow() + 1;
    }

}
