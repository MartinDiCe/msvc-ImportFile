package com.diceprojects.importcsvmeli.controllers;

import com.diceprojects.importcsvmeli.dto.ImportResponseDTO;
import com.diceprojects.importcsvmeli.services.FileImportservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/msvc-importmeli")
public class FileImportController {
    private final FileImportservice fileImportService;

    @Autowired
    public FileImportController(FileImportservice fileImportService) {
        this.fileImportService = fileImportService;
    }

    @PostMapping("/process")
    public ResponseEntity<ImportResponseDTO> importFile(
            @RequestParam("filePath") String filePath,
            @RequestParam("fileName") String fileName
    ) {
        ImportResponseDTO result = fileImportService.importFile(filePath, fileName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
