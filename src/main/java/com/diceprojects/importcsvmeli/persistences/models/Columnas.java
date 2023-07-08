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
    private int rutaMeliMapping;

    @Column(name = "ruta_referencia")
    private int rutaReferenciaMapping;

    @Column(name = "fecha_inicio_plan")
    private int fechaInicioPlanMapping;

    @Column(name = "fecha_fin_plan")
    private int fechaFinPlanMapping;

    @Column(name = "tipo_vehiculo")
    private int tipoVehiculoMapping;

    @Column(name = "vehiculo_id")
    private int vehiculoIdMapping;

    @Column(name = "patente_vehiculo_tractor")
    private int patenteVehiculoTractorMapping;

    @Column(name = "patente_vehiculo_carga1")
    private int patenteVehiculoCarga1Mapping;

    @Column(name = "conductor_id")
    private int conductorIdMapping;

    @Column(name = "deposito_salida")
    private int depositoSalidaMapping;

    @Column(name = "deposito_llegada")
    private int depositoLlegadaMapping;

    @Column(name = "tipo_colecta")
    private int tipoColectaMapping;

    @Column(name = "tipo_ruta")
    private int tipoRutaMapping;

    @Column(name = "peso")
    private int pesoMapping;

    @Column(name = "volumen")
    private int volumenMapping;

    @Column(name = "pallets")
    private int palletsMapping;

    @Column(name = "paquetes")
    private int paquetesMapping;

    @Column(name = "id_parada")
    private int idParadaMapping;

    @Column(name = "parada")
    private int paradaMapping;

    @Column(name = "tipo_parada")
    private int tipoParadaMapping;

    @Column(name = "puntualidad")
    private int puntualidadMapping;

    @Column(name = "top_seller")
    private int topSellerMapping;

    @Column(name = "planificado")
    private int planificadoMapping;

    @Column(name = "descripcion")
    private int descripcionMapping;

    @Column(name = "operacion_referencia")
    private String operacionMapping;

    public Columnas(Long id, int rutaMeliMapping, int rutaReferenciaMapping, int fechaInicioPlanMapping, int fechaFinPlanMapping, int tipoVehiculoMapping, int vehiculoIdMapping, int patenteVehiculoTractorMapping, int patenteVehiculoCarga1Mapping, int conductorIdMapping, int depositoSalidaMapping, int depositoLlegadaMapping, int tipoColectaMapping, int tipoRutaMapping, int pesoMapping, int volumenMapping, int palletsMapping, int paquetesMapping, int idParadaMapping, int paradaMapping, int tipoParadaMapping, int puntualidadMapping, int topSellerMapping, int planificadoMapping, int descripcionMapping, String operacionMapping) {
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
        this.operacionMapping = operacionMapping;
    }

    public Columnas() {

    }
}
