package com.diceprojects.importcsvmeli.persistences.repositories;

import com.diceprojects.importcsvmeli.persistences.models.Columnas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnasRepository extends JpaRepository<Columnas, Long> {
    Columnas findByOperacionMapping(String operacion);
}
