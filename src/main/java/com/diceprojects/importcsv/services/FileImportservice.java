package com.diceprojects.importcsv.services;

import com.diceprojects.importcsv.persistences.models.FileColumnsHeader;
import com.diceprojects.importcsv.persistences.models.dto.ImportResponseDTO;
import com.diceprojects.importcsv.persistences.models.FileColumns;

import java.util.List;
import java.util.Optional;

public interface FileImportservice {
    Optional<ImportResponseDTO> importFile(String filePath, String fileName);
    List<String[]> readCSVFile(String file, String delimitadorArchivoMapping);
    String getValueFromMapping(String[] row, int columnIndex);
    FileColumnsHeader getConfigColumnFromFileName(String fileName);

}
