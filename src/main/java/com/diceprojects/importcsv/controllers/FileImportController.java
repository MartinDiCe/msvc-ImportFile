package com.diceprojects.importcsv.controllers;

import com.diceprojects.importcsv.persistences.models.dto.ImportResponseDTO;
import com.diceprojects.importcsv.services.FileImportservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<?> importFile(
            @RequestParam("filePath") String filePath,
            @RequestParam("fileName") String fileName
    ) {

            Optional<ImportResponseDTO> o = fileImportService.importFile(filePath, fileName);

            if (o.isEmpty()) {
                return ResponseEntity.status(HttpStatus
                        .NO_CONTENT).build();
            }


            return ResponseEntity.status(HttpStatus
                    .OK).build();

    }


}
