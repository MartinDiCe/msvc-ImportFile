package com.diceprojects.importcsv.services;

import com.diceprojects.importcsv.persistences.models.dto.ImportResponseDTO;
import com.diceprojects.importcsv.persistences.models.Columns;

import java.util.List;
import java.util.Optional;

public interface FileImportservice {
    Optional<ImportResponseDTO> importFile(String filePath, String fileName);
    List<String[]> readCSVFile(String file, char delimitadorArchivoMapping);
    String getValueFromMapping(String[] row, int columnIndex);
    Columns getConfigColumnFromFileName(String fileName);

}
