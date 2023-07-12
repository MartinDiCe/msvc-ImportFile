package com.diceprojects.importcsv.controllers;

import com.diceprojects.importcsv.dto.ImportResponseDTO;
import com.diceprojects.importcsv.services.FileImportservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/msvc-importCSV")
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
