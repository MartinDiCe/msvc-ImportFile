package com.diceprojects.importfile.services;

import com.diceprojects.importfile.persistences.models.FileColumnsHeader;
import com.diceprojects.importfile.persistences.models.dto.ImportResponseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para definir las operaciones relacionadas con la importación de archivos en msvc-importCSV.
 */
public interface FileImportService {

    /**
     * Importa un archivo y guarda los registros en la base de datos.
     *
     * @param filePath Ruta del archivo a importar.
     * @param fileName Nombre del archivo a importar.
     * @return Optional con el resultado de la operación de importación.
     */
    Optional<ImportResponseDTO> importFile(String filePath, String fileName);

    /**
     * Lee un archivo CSV y devuelve una lista de registros.
     *
     * @param file                  Ruta del archivo CSV.
     * @param delimitadorArchivoMapping Delimitador utilizado en el archivo CSV.
     * @return Lista de registros leídos del archivo CSV.
     */
    List<String[]> readCSVFile(String file, String delimitadorArchivoMapping);

    /**
     * Obtiene el valor de una columna específica en un registro.
     *
     * @param row        Registro del cual obtener el valor.
     * @param columnIndex Índice de la columna en el registro.
     * @return Valor de la columna en el registro.
     */
    String getValueFromMapping(String[] row, int columnIndex);

    /**
     * Obtiene la configuración de columnas para un nombre de archivo específico.
     *
     * @param fileName Nombre del archivo.
     * @return Objeto FileColumnsHeader que representa la configuración de columnas.
     */
    FileColumnsHeader getConfigColumnFromFileName(String fileName);
}
