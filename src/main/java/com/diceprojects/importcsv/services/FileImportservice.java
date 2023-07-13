package com.diceprojects.importcsv.services;

import com.diceprojects.importcsv.dto.ImportResponseDTO;
import com.diceprojects.importcsv.persistences.models.Columns;

import java.util.List;

public interface FileImportservice {
    ImportResponseDTO importFile(String filePath, String fileName);
    List<String[]> readCSVFile(String file, char delimitadorArchivoMapping);
    String getValueFromMapping(String[] row, int columnIndex);
    Columns getConfigColumnFromFileName(String fileName);

}
