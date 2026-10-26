package com.ejemplo.restaurante.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class MesaRequestDTO {

    @NotNull(message = "El número de mesa es obligatorio")
    @Positive(message = "El número de mesa debe ser un valor positivo")
    private Integer numeroMesa;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser mayor a cero")
    private Integer capacidad;

    @NotBlank(message = "La ubicación es obligatoria")
    @Size(max = 80, message = "La ubicación admite máximo 80 caracteres")
    private String ubicacion;



    public MesaRequestDTO() {}



    public Integer getNumeroMesa() { return numeroMesa; }
    public void setNumeroMesa(Integer numeroMesa) { this.numeroMesa = numeroMesa; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
