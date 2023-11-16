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

public class ExcelToCsvConverter {

    public static String convertExcelToCsv(String filePath, String fileName, String delimitador) throws IOException {
        try {
            Workbook workbook = getWorkbook(filePath, fileName);

            CreationHelper creationHelper = workbook.getCreationHelper();
            FormulaEvaluator evaluator = creationHelper.createFormulaEvaluator();
            evaluator.setIgnoreMissingWorkbooks(true);
            evaluator.evaluateAll();

            Sheet sheet = workbook.getSheetAt(0);

            StringBuilder csvContent = new StringBuilder();

            for (Row row : sheet) {
                List<String> rowData = getRowData(row);
                Iterator<String> rowDataIterator = rowData.iterator();

                while (rowDataIterator.hasNext()) {
                    String cellValue = rowDataIterator.next();
                    csvContent.append(cellValue);

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

    private static Workbook getWorkbook(String filePath, String fileName) throws IOException {
        if (fileName.endsWith(".xls")) {
            return new HSSFWorkbook(new FileInputStream(new File(filePath + fileName)));
        } else if (fileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(new FileInputStream(new File(filePath + fileName)));
        } else {
            throw new IllegalArgumentException("El archivo no es un archivo Excel válido (.xls o .xlsx)");
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            switch (cell.getCellType()) {
                case BLANK:
                    return " ";
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

    private static List<String> getRowData(Row row) {
        List<String> rowData = new ArrayList<>();
        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            rowData.add(getCellValue(cell));
        }

        return rowData;
    }

    private static String saveCsvFile(String filePath, String fileName, String csvContent) throws IOException {
        String csvFileName = fileName.replaceFirst("[.][^.]+$", "") + ".csv";
        String csvFilePath = filePath + csvFileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write(csvContent);
        }

        return csvFilePath;
    }
}
