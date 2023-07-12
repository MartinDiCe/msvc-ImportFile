package com.diceprojects.importcsvmeli.persistences.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "columnas_Meli")
public class Columnas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ruta_meli")
    private Integer rutaMeliMapping;

    @Column(name = "ruta_referencia")
    private Integer rutaReferenciaMapping;

    @Column(name = "fecha_inicio_plan")
    private Integer fechaInicioPlanMapping;

    @Column(name = "fecha_fin_plan")
    private Integer fechaFinPlanMapping;

    @Column(name = "tipo_vehiculo")
    private Integer tipoVehiculoMapping;

    @Column(name = "vehiculo_id")
    private Integer vehiculoIdMapping;

    @Column(name = "patente_vehiculo_tractor")
    private Integer patenteVehiculoTractorMapping;

    @Column(name = "patente_vehiculo_carga1")
    private Integer patenteVehiculoCarga1Mapping;

    @Column(name = "conductor_id")
    private Integer conductorIdMapping;

    @Column(name = "deposito_salida")
    private Integer depositoSalidaMapping;

    @Column(name = "deposito_llegada")
    private Integer depositoLlegadaMapping;

    @Column(name = "tipo_colecta")
    private Integer tipoColectaMapping;

    @Column(name = "tipo_ruta")
    private Integer tipoRutaMapping;

    @Column(name = "peso")
    private Integer pesoMapping;

    @Column(name = "volumen")
    private Integer volumenMapping;

    @Column(name = "pallets")
    private Integer palletsMapping;

    @Column(name = "paquetes")
    private Integer paquetesMapping;

    @Column(name = "id_parada")
    private Integer idParadaMapping;

    @Column(name = "parada")
    private Integer paradaMapping;

    @Column(name = "tipo_parada")
    private Integer tipoParadaMapping;

    @Column(name = "puntualidad")
    private Integer puntualidadMapping;

    @Column(name = "top_seller")
    private Integer topSellerMapping;

    @Column(name = "planificado")
    private Integer planificadoMapping;

    @Column(name = "descripcion")
    private Integer descripcionMapping;

    @Column(name = "delimitador_archivo")
    private char delimitadorArchivoMapping;

    @Column(name = "operacion_referencia")
    private String operacionMapping;

    public Columnas(Long id, Integer rutaMeliMapping, Integer rutaReferenciaMapping, Integer fechaInicioPlanMapping, Integer fechaFinPlanMapping, Integer tipoVehiculoMapping, Integer vehiculoIdMapping, Integer patenteVehiculoTractorMapping, Integer patenteVehiculoCarga1Mapping, Integer conductorIdMapping, Integer depositoSalidaMapping, Integer depositoLlegadaMapping, Integer tipoColectaMapping, Integer tipoRutaMapping, Integer pesoMapping, Integer volumenMapping, Integer palletsMapping, Integer paquetesMapping, Integer idParadaMapping, Integer paradaMapping, Integer tipoParadaMapping, Integer puntualidadMapping, Integer topSellerMapping, Integer planificadoMapping, Integer descripcionMapping, String operacionMapping, char delimitadorArchivo) {
        this.id = id;
        this.rutaMeliMapping = rutaMeliMapping;
        this.rutaReferenciaMapping = rutaReferenciaMapping;
        this.fechaInicioPlanMapping = fechaInicioPlanMapping;
        this.fechaFinPlanMapping = fechaFinPlanMapping;
        this.tipoVehiculoMapping = tipoVehiculoMapping;
        this.vehiculoIdMapping = vehiculoIdMapping;
        this.patenteVehiculoTractorMapping = patenteVehiculoTractorMapping;
        this.patenteVehiculoCarga1Mapping = patenteVehiculoCarga1Mapping;
        this.conductorIdMapping = conductorIdMapping;
        this.depositoSalidaMapping = depositoSalidaMapping;
        this.depositoLlegadaMapping = depositoLlegadaMapping;
        this.tipoColectaMapping = tipoColectaMapping;
        this.tipoRutaMapping = tipoRutaMapping;
        this.pesoMapping = pesoMapping;
        this.volumenMapping = volumenMapping;
        this.palletsMapping = palletsMapping;
        this.paquetesMapping = paquetesMapping;
        this.idParadaMapping = idParadaMapping;
        this.paradaMapping = paradaMapping;
        this.tipoParadaMapping = tipoParadaMapping;
        this.puntualidadMapping = puntualidadMapping;
        this.topSellerMapping = topSellerMapping;
        this.planificadoMapping = planificadoMapping;
        this.descripcionMapping = descripcionMapping;
        this.delimitadorArchivoMapping = delimitadorArchivo;
        this.operacionMapping = operacionMapping;
    }

    public Columnas() {

    }
}
