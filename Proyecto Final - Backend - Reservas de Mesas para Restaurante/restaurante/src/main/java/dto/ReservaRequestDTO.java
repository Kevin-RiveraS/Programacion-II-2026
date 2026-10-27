package com.ejemplo.restaurante.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "El teléfono del cliente es obligatorio")
    private String telefonoCliente;

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
}
