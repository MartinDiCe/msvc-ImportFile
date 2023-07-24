package com.diceprojects.importcsv.clients;

import com.diceprojects.importcsv.persistences.models.FileColumnsHeader;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-columns", url="localhost:8001/msvc-columns")
public interface ColumnsClientRestApi {

    @GetMapping("/get-config")
    FileColumnsHeader getConfigColumnFromFileName(@RequestParam("fileName") String fileName);
    @GetMapping("/list")
    List<FileColumnsHeader> listAllColumns();
    @GetMapping("/find-by-operacion")
    FileColumnsHeader findByOperacionProcesoMapping(@RequestParam("operacion") String operacion);

}