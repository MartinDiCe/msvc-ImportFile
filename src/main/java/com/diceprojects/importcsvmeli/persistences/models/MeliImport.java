package com.diceprojects.importcsvmeli.persistences.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ZM_Importacion_MELI")
public class MeliImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "RutaMeli")
    private String rutaMeli;

    @Column(name = "RutaReferencia")
    private String rutaReferencia;

    @Column(name = "FechaInicioPlan")
    private Date fechaInicioPlan;

    @Column(name = "FechaFinPlan")
    private Date fechaFinPlan;

    @Column(name = "TipoVehiculo")
    private String tipoVehiculo;

    @Column(name = "VehiculoID")
    private String vehiculoID;

    @Column(name = "PatenteVehiculoTractor")
    private String patenteVehiculoTractor;

    @Column(name = "PatenteVehiculoCarga1")
    private String patenteVehiculoCarga1;

    @Column(name = "ConductorId")
    private String conductorId;

    @Column(name = "DepositoSalida")
    private String depositoSalida;

    @Column(name = "DepositoLlegada")
    private String depositoLlegada;

    @Column(name = "TipoColecta")
    private String tipoColecta;

    @Column(name = "TipoRuta")
    private String tipoRuta;

    @Column(name = "Peso")
    private String peso;

    @Column(name = "Volumen")
    private String volumen;

    @Column(name = "Pallets")
    private String pallets;

    @Column(name = "Paquetes")
    private Integer paquetes;

    @Column(name = "IdParada")
    private String idParada;

    @Column(name = "Parada")
    private String parada;

    @Column(name = "TipoParada")
    private String tipoParada;

    @Column(name = "Puntualidad")
    private String puntualidad;

    @Column(name = "TOPSeller")
    private String topSeller;

    @Column(name = "Planificado")
    private String planificado;

    @Column(name = "Descripcion", length = 500)
    private String descripcion;

    @Column(name = "Operacion")
    private String operacion;

    @Column(name = "ArchivoImportacion")
    private String archivoImportacion;

    public MeliImport(Integer id, String rutaMeli, String rutaReferencia, Date fechaInicioPlan, Date fechaFinPlan, String tipoVehiculo, String vehiculoID, String patenteVehiculoTractor, String patenteVehiculoCarga1, String conductorId, String depositoSalida, String depositoLlegada, String tipoColecta, String tipoRuta, String peso, String volumen, String pallets, Integer paquetes, String idParada, String parada, String tipoParada, String puntualidad, String topSeller, String planificado, String descripcion, String operacion, String archivoImportacion) {
        this.id = id;
        this.rutaMeli = rutaMeli;
        this.rutaReferencia = rutaReferencia;
        this.fechaInicioPlan = fechaInicioPlan;
        this.fechaFinPlan = fechaFinPlan;
        this.tipoVehiculo = tipoVehiculo;
        this.vehiculoID = vehiculoID;
        this.patenteVehiculoTractor = patenteVehiculoTractor;
        this.patenteVehiculoCarga1 = patenteVehiculoCarga1;
        this.conductorId = conductorId;
        this.depositoSalida = depositoSalida;
        this.depositoLlegada = depositoLlegada;
        this.tipoColecta = tipoColecta;
        this.tipoRuta = tipoRuta;
        this.peso = peso;
        this.volumen = volumen;
        this.pallets = pallets;
        this.paquetes = paquetes;
        this.idParada = idParada;
        this.parada = parada;
        this.tipoParada = tipoParada;
        this.puntualidad = puntualidad;
        this.topSeller = topSeller;
        this.planificado = planificado;
        this.descripcion = descripcion;
        this.operacion = operacion;
        this.archivoImportacion = archivoImportacion;
    }

    public MeliImport() {

    }
}
