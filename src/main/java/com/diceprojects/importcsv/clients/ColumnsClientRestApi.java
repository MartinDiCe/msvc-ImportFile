package com.diceprojects.importcsv.clients;

import com.diceprojects.importcsv.persistences.models.Columns;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-columns", url="localhost:8001/msvc-columns")
public interface ColumnsClientRestApi {

    @GetMapping("/get-config-columns")
    ResponseEntity<Columns> getConfigColumnFromFileName(@RequestParam("fileName") String fileName);
    @GetMapping("/list-all-columns")
    ResponseEntity <List<Columns>> listAllColumns();
    @GetMapping("/find-columns_by_operacion")
    ResponseEntity <Columns> findByOperacionProcesoMapping(@RequestParam("operacion") String operacion);

}