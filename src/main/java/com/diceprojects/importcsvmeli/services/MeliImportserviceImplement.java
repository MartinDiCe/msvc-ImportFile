package com.diceprojects.importcsvmeli.services;

import com.diceprojects.importcsvmeli.dto.ImportResponseDTO;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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
import org.springframework.stereotype.Service;

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
    public ImportResponseDTO importFile(String filePath, String fileName) {
        String file = new StringBuilder()
                .append(filePath)
                .append(fileName)
                .toString();

        String operacion = getOperacionFromFileName(fileName);

        Columnas columnas = columnasRepository.findByOperacionMapping(operacion);

        if (columnas == null) {
            throw new ColumnasNoEncontradasException(operacion);
        }

        MeliImport existingImport = repository.findFirstByArchivoImportacion(fileName);
        if (existingImport != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El archivo '" + fileName + "' ya ha sido importado anteriormente.");
        }

        List<String[]> rows = readCSVFile(file, columnas.getDelimitadorArchivoMapping());
        int processedRows = 0;

        try {
            boolean firstRowSkipped = false;
            for (String[] row : rows) {
                if (!firstRowSkipped) {
                    firstRowSkipped = true;
                    continue;
                }

                try {
                    MeliImport meliImport = new MeliImport();

                    if (columnas.getFechaInicioPlanMapping() != null) {
                        String fechaInicioPlanStr = getValueFromMapping(row, columnas.getFechaInicioPlanMapping());
                        if (fechaInicioPlanStr != null) {
                            Date fechaInicioPlan = parseDate(fechaInicioPlanStr);
                            meliImport.setFechaInicioPlan(fechaInicioPlan);
                        }
                    }

                    if (columnas.getFechaFinPlanMapping() != null) {
                        String fechaFinPlanStr = getValueFromMapping(row, columnas.getFechaFinPlanMapping());
                        if (fechaFinPlanStr != null) {
                            Date fechaFinPlan = parseDate(fechaFinPlanStr);
                            meliImport.setFechaFinPlan(fechaFinPlan);
                        }
                    }

                    if (columnas.getPesoMapping() != null) {
                        String pesoStr = getValueFromMapping(row, columnas.getPesoMapping());
                        if (pesoStr != null) {
                            meliImport.setPeso(pesoStr);
                        }
                    }

                    if (columnas.getVolumenMapping() != null) {
                        String volumenStr = getValueFromMapping(row, columnas.getVolumenMapping());
                        if (volumenStr != null) {
                            meliImport.setVolumen(volumenStr);
                        }
                    }

                    if (columnas.getPaquetesMapping() != null) {
                        String paquetesStr = getValueFromMapping(row, columnas.getPaquetesMapping());
                        if (paquetesStr != null) {
                            Integer paquetes = Integer.parseInt(paquetesStr);
                            meliImport.setPaquetes(paquetes);
                        }
                    }

                    if (columnas.getRutaMeliMapping() != null) {
                        String rutaMeliStr = getValueFromMapping(row, columnas.getRutaMeliMapping());
                        if (rutaMeliStr != null) {
                            meliImport.setRutaMeli(rutaMeliStr);
                        }
                    }

                    if (columnas.getRutaReferenciaMapping() != null) {
                        String rutaReferenciaStr = getValueFromMapping(row, columnas.getRutaReferenciaMapping());
                        if (rutaReferenciaStr != null) {
                            meliImport.setRutaReferencia(rutaReferenciaStr);
                        }
                    }

                    if (columnas.getTipoVehiculoMapping() != null) {
                        String tipoVehiculoStr = getValueFromMapping(row, columnas.getTipoVehiculoMapping());
                        if (tipoVehiculoStr != null) {
                            meliImport.setTipoVehiculo(tipoVehiculoStr);
                        }
                    }

                    if (columnas.getVehiculoIdMapping() != null) {
                        String vehiculoIdStr = getValueFromMapping(row, columnas.getVehiculoIdMapping());
                        if (vehiculoIdStr != null) {
                            meliImport.setVehiculoID(vehiculoIdStr);
                        }
                    }

                    if (columnas.getPatenteVehiculoTractorMapping() != null) {
                        String patenteVehiculoTractorStr = getValueFromMapping(row, columnas.getPatenteVehiculoTractorMapping());
                        if (patenteVehiculoTractorStr != null) {
                            meliImport.setPatenteVehiculoTractor(patenteVehiculoTractorStr);
                        }
                    }

                    if (columnas.getPatenteVehiculoCarga1Mapping() != null) {
                        String patenteVehiculoCargaStr = getValueFromMapping(row, columnas.getPatenteVehiculoCarga1Mapping());
                        if (patenteVehiculoCargaStr != null) {
                            meliImport.setPatenteVehiculoCarga1(patenteVehiculoCargaStr);
                        }
                    }

                    if (columnas.getConductorIdMapping() != null) {
                        String conductorIdStr = getValueFromMapping(row, columnas.getConductorIdMapping());
                        if (conductorIdStr != null) {
                            meliImport.setConductorId(conductorIdStr);
                        }
                    }

                    if (columnas.getDepositoSalidaMapping() != null) {
                        String depositoSalidaStr = getValueFromMapping(row, columnas.getDepositoSalidaMapping());
                        if (depositoSalidaStr != null) {
                            meliImport.setDepositoSalida(depositoSalidaStr);
                        }
                    }

                    if (columnas.getDepositoLlegadaMapping() != null) {
                        String depositoLlegadaStr = getValueFromMapping(row, columnas.getDepositoLlegadaMapping());
                        if (depositoLlegadaStr != null) {
                            meliImport.setDepositoLlegada(depositoLlegadaStr);
                        }
                    }

                    if (columnas.getTipoColectaMapping() != null) {
                        String tipoColectaStr = getValueFromMapping(row, columnas.getTipoColectaMapping());
                        if (tipoColectaStr != null) {
                            meliImport.setTipoColecta(tipoColectaStr);
                        }
                    }

                    if (columnas.getTipoRutaMapping() != null) {
                        String tipoRutaStr = getValueFromMapping(row, columnas.getTipoRutaMapping());
                        if (tipoRutaStr != null) {
                            meliImport.setTipoRuta(tipoRutaStr);
                        }
                    }

                    if (columnas.getPalletsMapping() != null) {
                        String palletsStr = getValueFromMapping(row, columnas.getPalletsMapping());
                        if (palletsStr != null) {
                            meliImport.setPallets(palletsStr);
                        }
                    }

                    if (columnas.getIdParadaMapping() != null) {
                        String idParadaStr = getValueFromMapping(row, columnas.getIdParadaMapping());
                        if (idParadaStr != null) {
                            meliImport.setIdParada(idParadaStr);
                        }
                    }

                    if (columnas.getParadaMapping() != null) {
                        String paradaStr = getValueFromMapping(row, columnas.getParadaMapping());
                        if (paradaStr != null) {
                            meliImport.setParada(paradaStr);
                        }
                    }

                    if (columnas.getTipoParadaMapping() != null) {
                        String tipoParadaStr = getValueFromMapping(row, columnas.getTipoParadaMapping());
                        if (tipoParadaStr != null) {
                            meliImport.setTipoParada(tipoParadaStr);
                        }
                    }

                    if (columnas.getPuntualidadMapping() != null) {
                        String puntualidadStr = getValueFromMapping(row, columnas.getPuntualidadMapping());
                        if (puntualidadStr != null) {
                            meliImport.setPuntualidad(puntualidadStr);
                        }
                    }

                    if (columnas.getTopSellerMapping() != null) {
                        String topSellerStr = getValueFromMapping(row, columnas.getTopSellerMapping());
                        if (topSellerStr != null) {
                            meliImport.setTopSeller(topSellerStr);
                        }
                    }

                    if (columnas.getPlanificadoMapping() != null) {
                        String planificadoStr = getValueFromMapping(row, columnas.getPlanificadoMapping());
                        if (planificadoStr != null) {
                            meliImport.setPlanificado(planificadoStr);
                        }
                    }

                    if (columnas.getDescripcionMapping() != null) {
                        String descripcionStr = getValueFromMapping(row, columnas.getDescripcionMapping());
                        if (descripcionStr != null) {
                            if (descripcionStr.length() > 500) {
                                descripcionStr = descripcionStr.substring(0, 500);
                            }
                            meliImport.setDescripcion(descripcionStr);
                        }
                    }

                    meliImport.setOperacion(operacion);
                    meliImport.setArchivoImportacion(fileName);

                    repository.save(meliImport);

                    processedRows++;

                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al importar archivo", e);
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al importar archivo", e);
        }

        ImportResponseDTO response = new ImportResponseDTO();
        response.setStatus("200");
        response.setTitle("Importación exitosa");
        response.setDetail("Cantidad de lineas leídas: "+ processedRows);
        return response;
    }

    public List<String[]> readCSVFile(String file, char delimitadorArchivoMapping) {
        List<String[]> rows;
        String delimitador = String.valueOf(delimitadorArchivoMapping);

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                .withCSVParser(new CSVParserBuilder().withSeparator(delimitador.charAt(0)).build())
                .build()) {
            rows = reader.readAll();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer el contenido del archivo", e);
        } catch (CsvException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer el contenido del archivo CSV", e);
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

    private Date parseDate(String dateValue) {
        List<String> dateFormats = new ArrayList<>();
        dateFormats.add("yyyy-MM-dd");
        dateFormats.add("dd/MM/yyyy");

        for (String dateFormat : dateFormats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                Date parsedDate = sdf.parse(dateValue);
                return parsedDate;
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
