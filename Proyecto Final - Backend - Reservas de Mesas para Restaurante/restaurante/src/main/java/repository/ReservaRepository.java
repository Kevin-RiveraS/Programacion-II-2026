package com.ejemplo.restaurante.repository;

import com.ejemplo.restaurante.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {




    List<Reserva> findByFecha(LocalDate fecha);



    List<Reserva> findByMesaIdAndFecha(Long mesaId, LocalDate fecha);



    List<Reserva> findByTelefonoCliente(String telefonoCliente);
}
