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
import com.diceprojects.importcsvmeli.persistences.models.FileImport;
import com.diceprojects.importcsvmeli.persistences.repositories.ColumnasRepository;
import com.diceprojects.importcsvmeli.persistences.repositories.FileImportRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FileImportserviceImplement implements FileImportservice {

    private final FileImportRepository repository;
    private final ColumnasRepository columnasRepository;

    public FileImportserviceImplement(FileImportRepository repository, ColumnasRepository columnasRepository) {
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

        FileImport existingImport = repository.findFirstByArchivoImportacion(fileName);
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
                    FileImport fileImport = new FileImport();

                    if (columnas.getColumna2Mapping() != null) {
                        String fechaInicioPlanStr = getValueFromMapping(row, columnas.getColumna2Mapping());
                        if (fechaInicioPlanStr != null) {
                            Date fechaInicioPlan = parseDate(fechaInicioPlanStr);
                            fileImport.setFechaInicioPlan(fechaInicioPlan);
                        }
                    }

                    if (columnas.getColumna3Mapping() != null) {
                        String fechaFinPlanStr = getValueFromMapping(row, columnas.getColumna3Mapping());
                        if (fechaFinPlanStr != null) {
                            Date fechaFinPlan = parseDate(fechaFinPlanStr);
                            fileImport.setFechaFinPlan(fechaFinPlan);
                        }
                    }

                    if (columnas.getColumna13Mapping() != null) {
                        String pesoStr = getValueFromMapping(row, columnas.getColumna13Mapping());
                        if (pesoStr != null) {
                            fileImport.setPeso(pesoStr);
                        }
                    }

                    if (columnas.getColumna14Mapping() != null) {
                        String volumenStr = getValueFromMapping(row, columnas.getColumna14Mapping());
                        if (volumenStr != null) {
                            fileImport.setVolumen(volumenStr);
                        }
                    }

                    if (columnas.getColumna16Mapping() != null) {
                        String paquetesStr = getValueFromMapping(row, columnas.getColumna16Mapping());
                        if (paquetesStr != null) {
                            Integer paquetes = Integer.parseInt(paquetesStr);
                            fileImport.setPaquetes(paquetes);
                        }
                    }

                    if (columnas.getColumna0Mapping() != null) {
                        String rutaMeliStr = getValueFromMapping(row, columnas.getColumna0Mapping());
                        if (rutaMeliStr != null) {
                            fileImport.setRutaMeli(rutaMeliStr);
                        }
                    }

                    if (columnas.getColumna1Mapping() != null) {
                        String rutaReferenciaStr = getValueFromMapping(row, columnas.getColumna1Mapping());
                        if (rutaReferenciaStr != null) {
                            fileImport.setRutaReferencia(rutaReferenciaStr);
                        }
                    }

                    if (columnas.getColumna4Mapping() != null) {
                        String tipoVehiculoStr = getValueFromMapping(row, columnas.getColumna4Mapping());
                        if (tipoVehiculoStr != null) {
                            fileImport.setTipoVehiculo(tipoVehiculoStr);
                        }
                    }

                    if (columnas.getColumna5Mapping() != null) {
                        String vehiculoIdStr = getValueFromMapping(row, columnas.getColumna5Mapping());
                        if (vehiculoIdStr != null) {
                            fileImport.setVehiculoID(vehiculoIdStr);
                        }
                    }

                    if (columnas.getColumna6Mapping() != null) {
                        String patenteVehiculoTractorStr = getValueFromMapping(row, columnas.getColumna6Mapping());
                        if (patenteVehiculoTractorStr != null) {
                            fileImport.setPatenteVehiculoTractor(patenteVehiculoTractorStr);
                        }
                    }

                    if (columnas.getColumna7Mapping() != null) {
                        String patenteVehiculoCargaStr = getValueFromMapping(row, columnas.getColumna7Mapping());
                        if (patenteVehiculoCargaStr != null) {
                            fileImport.setPatenteVehiculoCarga1(patenteVehiculoCargaStr);
                        }
                    }

                    if (columnas.getColumna8Mapping() != null) {
                        String conductorIdStr = getValueFromMapping(row, columnas.getColumna8Mapping());
                        if (conductorIdStr != null) {
                            fileImport.setConductorId(conductorIdStr);
                        }
                    }

                    if (columnas.getColumna9Mapping() != null) {
                        String depositoSalidaStr = getValueFromMapping(row, columnas.getColumna9Mapping());
                        if (depositoSalidaStr != null) {
                            fileImport.setDepositoSalida(depositoSalidaStr);
                        }
                    }

                    if (columnas.getColumna10Mapping() != null) {
                        String depositoLlegadaStr = getValueFromMapping(row, columnas.getColumna10Mapping());
                        if (depositoLlegadaStr != null) {
                            fileImport.setDepositoLlegada(depositoLlegadaStr);
                        }
                    }

                    if (columnas.getColumna11Mapping() != null) {
                        String tipoColectaStr = getValueFromMapping(row, columnas.getColumna11Mapping());
                        if (tipoColectaStr != null) {
                            fileImport.setTipoColecta(tipoColectaStr);
                        }
                    }

                    if (columnas.getColumna12Mapping() != null) {
                        String tipoRutaStr = getValueFromMapping(row, columnas.getColumna12Mapping());
                        if (tipoRutaStr != null) {
                            fileImport.setTipoRuta(tipoRutaStr);
                        }
                    }

                    if (columnas.getColumna15Mapping() != null) {
                        String palletsStr = getValueFromMapping(row, columnas.getColumna15Mapping());
                        if (palletsStr != null) {
                            fileImport.setPallets(palletsStr);
                        }
                    }

                    if (columnas.getColumna17Mapping() != null) {
                        String idParadaStr = getValueFromMapping(row, columnas.getColumna17Mapping());
                        if (idParadaStr != null) {
                            fileImport.setIdParada(idParadaStr);
                        }
                    }

                    if (columnas.getColumna18Mapping() != null) {
                        String paradaStr = getValueFromMapping(row, columnas.getColumna18Mapping());
                        if (paradaStr != null) {
                            fileImport.setParada(paradaStr);
                        }
                    }

                    if (columnas.getColumna19Mapping() != null) {
                        String tipoParadaStr = getValueFromMapping(row, columnas.getColumna19Mapping());
                        if (tipoParadaStr != null) {
                            fileImport.setTipoParada(tipoParadaStr);
                        }
                    }

                    if (columnas.getColumna20Mapping() != null) {
                        String puntualidadStr = getValueFromMapping(row, columnas.getColumna20Mapping());
                        if (puntualidadStr != null) {
                            fileImport.setPuntualidad(puntualidadStr);
                        }
                    }

                    if (columnas.getColumna21Mapping() != null) {
                        String topSellerStr = getValueFromMapping(row, columnas.getColumna21Mapping());
                        if (topSellerStr != null) {
                            fileImport.setTopSeller(topSellerStr);
                        }
                    }

                    if (columnas.getColumna22Mapping() != null) {
                        String planificadoStr = getValueFromMapping(row, columnas.getColumna22Mapping());
                        if (planificadoStr != null) {
                            fileImport.setPlanificado(planificadoStr);
                        }
                    }

                    if (columnas.getColumna23Mapping() != null) {
                        String descripcionStr = getValueFromMapping(row, columnas.getColumna23Mapping());
                        if (descripcionStr != null) {
                            if (descripcionStr.length() > 500) {
                                descripcionStr = descripcionStr.substring(0, 500);
                            }
                            fileImport.setDescripcion(descripcionStr);
                        }
                    }

                    fileImport.setOperacion(operacion);
                    fileImport.setArchivoImportacion(fileName);

                    repository.save(fileImport);

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
