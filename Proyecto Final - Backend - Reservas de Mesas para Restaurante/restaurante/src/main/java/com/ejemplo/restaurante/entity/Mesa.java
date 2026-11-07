package com.ejemplo.restaurante.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_mesa", nullable = false, unique = true)
    private Integer numeroMesa;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Boolean activa;

    @JsonManagedReference
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;


    public Mesa() {
    }


    public Mesa(Integer numeroMesa, Integer capacidad, String ubicacion, Boolean activa) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.activa = activa;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
