package com.diceprojects.importcsvmeli.services;

import com.diceprojects.importcsvmeli.dto.ImportResponseDTO;

import java.util.List;

public interface FileImportservice {
    ImportResponseDTO importFile(String filePath, String fileName);
    List<String[]> readCSVFile(String file, char delimitadorArchivoMapping);
    String getValueFromMapping(String[] row, int columnIndex);
    String getOperacionFromFileName(String fileName);

}
