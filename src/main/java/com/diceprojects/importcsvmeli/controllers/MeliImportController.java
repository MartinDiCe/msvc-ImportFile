package com.diceprojects.importcsvmeli.controllers;

import com.diceprojects.importcsvmeli.services.MeliImportservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/msvc-importmeli")
public class MeliImportController {
    private final MeliImportservice meliImportService;

    @Autowired
    public MeliImportController(MeliImportservice meliImportService) {
        this.meliImportService = meliImportService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importFile(
            @RequestParam("filePath") String filePath,
            @RequestParam("fileName") String fileName
    ) {
        String result = meliImportService.importFile(filePath, fileName);
        return ResponseEntity.ok(result);
    }
}
