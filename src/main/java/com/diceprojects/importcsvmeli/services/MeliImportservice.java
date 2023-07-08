package com.diceprojects.importcsvmeli.services;

import java.util.List;

public interface MeliImportservice {
    String importFile(String filePath, String fileName);
    List<String[]> readCSVFile(String filePath);
    String getValueFromMapping(String[] row, int columnIndex);
    String getOperacionFromFileName(String fileName);

}
