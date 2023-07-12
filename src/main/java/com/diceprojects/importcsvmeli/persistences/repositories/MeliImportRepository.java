package com.diceprojects.importcsvmeli.persistences.repositories;

import com.diceprojects.importcsvmeli.persistences.models.MeliImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeliImportRepository extends JpaRepository<MeliImport, Long> {
    MeliImport findFirstByArchivoImportacion(String fileName);

}
