package com.ejemplo.restaurante.repository;

import com.ejemplo.restaurante.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {


    Optional<Mesa> findByNumeroMesa(Integer numeroMesa);


    List<Mesa> findByActivaTrue();

    @Query("SELECT m FROM Mesa m WHERE m.activa= true AND m.id NOT IN"+
            "(SELECT r.mesa.id FROM Reserva r WHERE r.fecha = :fecha AND r.hora= :hora AND r.estado != 'CANCELADA')")
    List<Mesa> findMesasDisponibles(@Param("fecha") LocalDate fecha, @Param ("hora") LocalTime hora);

}
