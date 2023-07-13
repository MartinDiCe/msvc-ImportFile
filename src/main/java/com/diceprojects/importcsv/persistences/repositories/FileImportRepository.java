package com.diceprojects.importcsv.persistences.repositories;

import com.diceprojects.importcsv.persistences.models.FileImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileImportRepository extends JpaRepository<FileImport, Long> {
    FileImport findFirstByArchivoImportacion(String fileName);

}
