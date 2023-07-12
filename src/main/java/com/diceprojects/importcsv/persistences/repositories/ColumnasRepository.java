package com.diceprojects.importcsv.persistences.repositories;

import com.diceprojects.importcsv.persistences.models.Columnas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnasRepository extends JpaRepository<Columnas, Long> {
    Columnas findByOperacionMapping(String operacion);
}
