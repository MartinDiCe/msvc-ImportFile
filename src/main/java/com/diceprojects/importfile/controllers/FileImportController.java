package com.diceprojects.importfile.controllers;

import com.diceprojects.importfile.persistences.models.dto.ImportResponseDTO;
import com.diceprojects.importfile.services.FileImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controlador para gestionar las operaciones relacionadas con la importación de archivos en msvc-importCSV.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/msvc-importCSV")
public class FileImportController {

    private final FileImportService fileImportService;

    @Autowired
    public FileImportController(FileImportService fileImportService) {
        this.fileImportService = fileImportService;
    }

    /**
     * Procesa la importación de un archivo.
     *
     * @param filePath Ruta del archivo a importar.
     * @param fileName Nombre del archivo a importar.
     * @return ResponseEntity con el estado de la operación.
     */
    @PostMapping("/process")
    public ResponseEntity<?> importFile(
            @RequestParam("filePath") String filePath,
            @RequestParam("fileName") String fileName
    ) {
        Optional<ImportResponseDTO> importResponseDTO = fileImportService.importFile(filePath, fileName);

        if (importResponseDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
