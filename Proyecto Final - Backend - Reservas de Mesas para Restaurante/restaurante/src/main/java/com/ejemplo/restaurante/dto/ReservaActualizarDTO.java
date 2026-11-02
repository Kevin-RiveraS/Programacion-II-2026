package com.ejemplo.restaurante.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaActualizarDTO {

    @FutureOrPresent(message = "La fecha de la reserva debe ser actual o futura")
    private LocalDate fecha;
    private LocalTime hora;

    @Positive(message = "La cantidad de personas debe ser mayor a cero")
    private Integer cantidadPersonas;

    private Long mesaId;
    private String nombreCliente;

    @Pattern(regexp = "^\\d{8}$", message = "El número de teléfono tiene que tener ocho dígitos")
    private String telefonoCliente;



    @Pattern(regexp = "(?i)^(PENDIENTE|CONFIRMADA|CANCELADA|ATENDIDA)$",
            message = "El estado no es válido. Los datos permitidos son: PENDIENTE, CONFIRMADA, CANCELADA, ATENDIDA")
    private String estado;

    public ReservaActualizarDTO() {}



    public LocalDate getFecha() {
        return fecha; }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha; }

    public LocalTime getHora() {
        return hora; }
    public void setHora(LocalTime hora) {
        this.hora = hora; }

    public Integer getCantidadPersonas() {
        return cantidadPersonas; }
    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas; }

    public Long getMesaId() {
        return mesaId; }
    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId; }

    public String getNombreCliente() {
        return nombreCliente; }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente; }

    public String getTelefonoCliente() {
        return telefonoCliente; }
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente; }

    public String getEstado(){
        return estado; }
    public void setEstado(String estado){
        this.estado = estado; }
}
