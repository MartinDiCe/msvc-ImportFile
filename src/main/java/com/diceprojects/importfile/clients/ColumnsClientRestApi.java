package com.diceprojects.importfile.clients;

import com.diceprojects.importfile.persistences.models.FileColumnsHeader;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Cliente Feign para interactuar con el microservicio MSVC-Columns.
 */
@FeignClient(name = "msvc-columns", url = "localhost:8001/msvc-columns")
public interface ColumnsClientRestApi {

    /**
     * Obtiene la configuración de columnas para un archivo específico.
     *
     * @param fileName El nombre del archivo.
     * @return La configuración de columnas del archivo.
     */
    @GetMapping("/get-config")
    FileColumnsHeader getConfigColumnFromFileName(@RequestParam("fileName") String fileName);

    /**
     * Obtiene la lista de todas las columnas configuradas.
     *
     * @return Lista de todas las configuraciones de columnas.
     */
    @GetMapping("/list")
    List<FileColumnsHeader> listAllColumns();

    /**
     * Busca la configuración de columnas por la operación y proceso.
     *
     * @param operacion La operación a buscar.
     * @return La configuración de columnas encontrada.
     */
    @GetMapping("/find-by-operacion")
    FileColumnsHeader findByOperacionProcesoMapping(@RequestParam("operacion") String operacion);
}
