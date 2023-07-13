package com.diceprojects.importcsv.persistences.repositories;

import com.diceprojects.importcsv.persistences.models.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnsRepository extends JpaRepository<Columns, Long> {
    Columns findByOperacionProcesoMapping(String operacion);
}
