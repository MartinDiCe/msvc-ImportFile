package com.diceprojects.importcsvmeli.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.diceprojects.importcsvmeli.exceptions.ColumnasNoEncontradasException;
import com.diceprojects.importcsvmeli.persistences.models.Columnas;
import com.diceprojects.importcsvmeli.persistences.models.MeliImport;
import com.diceprojects.importcsvmeli.persistences.repositories.ColumnasRepository;
import com.diceprojects.importcsvmeli.persistences.repositories.MeliImportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class MeliImportserviceImplement implements MeliImportservice {

    private final MeliImportRepository repository;
    private final ColumnasRepository columnasRepository;

    public MeliImportserviceImplement(MeliImportRepository repository, ColumnasRepository columnasRepository) {
        this.repository = repository;
        this.columnasRepository = columnasRepository;
    }

    @Override
    public String importFile(String filePath, String fileName) {

        List<String[]> rows = readCSVFile(filePath);

        String operacion = getOperacionFromFileName(fileName);

        Columnas columnas = columnasRepository.findByOperacionMapping(operacion);

        if (columnas == null) {
            throw new ColumnasNoEncontradasException(operacion);
        }

        for (String[] row : rows) {

            String fechaInicioPlanStr = getValueFromMapping(row, columnas.getFechaInicioPlanMapping());
            Date fechaInicioPlan = parseDate(fechaInicioPlanStr).getBody();

            String fechaFinPlanStr = getValueFromMapping(row, columnas.getFechaFinPlanMapping());
            Date fechaFinPlan = parseDate(fechaFinPlanStr).getBody();

            String pesoStr = getValueFromMapping(row, columnas.getPesoMapping());
            BigDecimal peso = new BigDecimal(pesoStr);

            String volumenStr = getValueFromMapping(row, columnas.getVolumenMapping());
            BigDecimal volumen = new BigDecimal(volumenStr);

            String paquetesStr = getValueFromMapping(row, columnas.getPaquetesMapping());
            Integer paquetes = Integer.parseInt(paquetesStr);

            MeliImport meliImport = new MeliImport(
                    null,
                    getValueFromMapping(row, columnas.getRutaMeliMapping()),
                    getValueFromMapping(row, columnas.getRutaReferenciaMapping()),
                    fechaInicioPlan,
                    fechaFinPlan,
                    getValueFromMapping(row, columnas.getTipoVehiculoMapping()),
                    getValueFromMapping(row, columnas.getVehiculoIdMapping()),
                    getValueFromMapping(row, columnas.getPatenteVehiculoTractorMapping()),
                    getValueFromMapping(row, columnas.getPatenteVehiculoCarga1Mapping()),
                    getValueFromMapping(row, columnas.getConductorIdMapping()),
                    getValueFromMapping(row, columnas.getDepositoSalidaMapping()),
                    getValueFromMapping(row, columnas.getDepositoLlegadaMapping()),
                    getValueFromMapping(row, columnas.getTipoColectaMapping()),
                    getValueFromMapping(row, columnas.getTipoRutaMapping()),
                    peso,
                    volumen,
                    getValueFromMapping(row, columnas.getPalletsMapping()),
                    paquetes,
                    getValueFromMapping(row, columnas.getIdParadaMapping()),
                    getValueFromMapping(row, columnas.getParadaMapping()),
                    getValueFromMapping(row, columnas.getTipoParadaMapping()),
                    getValueFromMapping(row, columnas.getPuntualidadMapping()),
                    getValueFromMapping(row, columnas.getTopSellerMapping()),
                    getValueFromMapping(row, columnas.getPlanificadoMapping()),
                    getValueFromMapping(row, columnas.getDescripcionMapping()),
                    operacion,
                    fileName
            );


            try {
                repository.save(meliImport);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al importar archivo", e);
            }
        }

        return "Importación exitosa";
    }

    public List<String[]> readCSVFile(String filePath) {
        List<String[]> rows;

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            rows = reader.readAll();
        } catch (IOException | CsvException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer el contenido del archivo", e);
        }
        return rows;
    }

    public String getValueFromMapping(String[] row, int columnIndex) {
        try {
            if (columnIndex >= 0 && columnIndex < row.length) {
                return row[columnIndex];
            } else {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Las filas y la configuración de lectura de archivo no coinciden");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer las columnas de una fila", e);
        }
    }

    private ResponseEntity<Date> parseDate(String dateValue) {
        List<String> dateFormats = new ArrayList<>();
        dateFormats.add("yyyy-MM-dd");
        dateFormats.add("dd/MM/yyyy");

        for (String dateFormat : dateFormats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                Date parsedDate = sdf.parse(dateValue);
                return ResponseEntity.ok(parsedDate);
            } catch (ParseException e) {
                // El formato de fecha no coincide, intenta con el siguiente formato
            }
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "El formato de fecha no coincide con el esperado (yyyy-MM-dd o dd/MM/yyyy).");
    }

    public String getOperacionFromFileName(String fileName) {
        if (fileName.toLowerCase().startsWith("col")) {
            return "13";
        } else if (fileName.toLowerCase().startsWith("lh")) {
            return "16";
        } else if (fileName.toLowerCase().startsWith("del")) {
            return "20";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de archivo no corresponde al esperado col%, del% o lh%. Ejemplo: col202307071644");
        }
    }
}
