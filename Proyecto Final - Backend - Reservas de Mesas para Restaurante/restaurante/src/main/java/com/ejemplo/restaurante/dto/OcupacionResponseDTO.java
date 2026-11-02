package com.ejemplo.restaurante.dto;

public class OcupacionResponseDTO {
    private String fecha;
    private Double porcentajeOcupacion;

    public OcupacionResponseDTO(String fecha, Double porcentajeOcupacion) {
        this.fecha = fecha;
        this.porcentajeOcupacion = porcentajeOcupacion;
    }

    public String getFecha() {
        return fecha; }
    public void setFecha(String fecha) {
        this.fecha = fecha; }

    public Double getPorcentajeOcupacion() {
        return porcentajeOcupacion; }
    public void setPorcentajeOcupacion(Double porcentajeOcupacion) {
        this.porcentajeOcupacion = porcentajeOcupacion; }
}
