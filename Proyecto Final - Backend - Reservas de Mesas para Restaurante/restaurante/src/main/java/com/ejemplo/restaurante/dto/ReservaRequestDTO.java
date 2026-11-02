package com.ejemplo.restaurante.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRequestDTO {

    @NotNull(message = "La fecha de la reserva es obligatoria")
    @FutureOrPresent(message = "La fecha de la reserva debe ser actual o futura")
    private LocalDate fecha;

    @NotNull(message = "La hora de la reserva es obligatoria")
    private LocalTime hora;

    @NotNull(message = "La cantidad de personas es obligatoria")
    @Positive(message = "La cantidad de personas debe ser mayor a cero")
    private Integer cantidadPersonas;

    @NotNull(message = "El ID de la mesa es obligatorio")
    private Long mesaId;

    @jakarta.validation.constraints.NotBlank(message = "El nombre del cliente es obligatorio y no puede estar vacío")
    private String nombreCliente;

    @NotBlank(message = "El teléfono del cliente es obligatorio y no puede estar vacío")
    @Pattern(regexp = "^\\d{8}$", message = "El número de teléfono tiene que tener ocho dígitos")
    private String telefonoCliente;

    @NotBlank(message = "El estado es obligatorio al crear una reserva")
    @Pattern(regexp ="(?i)^(PENDIENTE|CONFIRMADA)$",
        message = "Al crear una reserva, el estado debe ser obligatoriamente PENDIENTE o CONFIRMADA")
    private String estado;


    public ReservaRequestDTO() {}

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
        return estado;
    }

    public void setEstado(String estado){
        this.estado= estado;
    }
}
