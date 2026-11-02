package com.ejemplo.restaurante.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaResponseDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private Integer numeroMesa;
    private int cantidadPersonas;
    private String nombreCliente;
    private String telefonoCliente;

    public ReservaResponseDTO(Long id, LocalDate fecha, LocalTime hora, String estado, Integer numeroMesa, int cantidadPersonas, String nombreCliente, String telefonoCliente) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.numeroMesa = numeroMesa;
        this.cantidadPersonas = cantidadPersonas;
        this.nombreCliente= nombreCliente;
        this.telefonoCliente= telefonoCliente;
    }

    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }

    public LocalDate getFecha() {
        return fecha; }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha; }

    public LocalTime getHora() {
        return hora; }
    public void setHora(LocalTime hora) {
        this.hora = hora; }

    public String getEstado() {
        return estado; }
    public void setEstado(String estado) {
        this.estado = estado; }

    public Integer getNumeroMesa() {
        return numeroMesa; }
    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa; }

    public int getCantidadPersonas() {
        return cantidadPersonas; }
    public void setCantidadPersonas(
            int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas; }


    public String getNombreCliente() {
        return nombreCliente; }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente; }


    public String getTelefonoCliente() {
        return telefonoCliente; }
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente; }

}
