package com.diceprojects.importfile.persistences.repositories;

import com.diceprojects.importfile.persistences.models.entities.FileImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link FileImport}.
 */
@Repository
public interface FileImportRepository extends JpaRepository<FileImport, Long> {

    /**
     * Encuentra el primer registro de importación de archivo por nombre de archivo.
     *
     * @param fileName Nombre del archivo de importación.
     * @return El primer registro de importación de archivo que coincide con el nombre especificado.
     */
    FileImport findFirstByArchivoImportacion(String fileName);

}

