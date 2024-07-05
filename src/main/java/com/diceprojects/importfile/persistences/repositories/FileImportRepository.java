package com.diceprojects.importfile.persistences.repositories;

import com.diceprojects.importfile.persistences.models.entities.FileImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

/**
 * Repositorio JPA para la entidad {@link FileImport}.
 */
@Repository
public interface FileImportRepository extends JpaRepository<FileImport, Long> {

}

