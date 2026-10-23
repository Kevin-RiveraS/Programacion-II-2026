package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.entity.Reserva;
import com.ejemplo.restaurante.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva guardarReserva(Reserva reserva) {

        if (reserva.getFecha() != null) {
            List<Reserva> existentes = reservaRepository.findByFecha(reserva.getFecha());

            boolean duplicada = existentes.stream().anyMatch(r ->
                    r.getHora() != null && r.getHora().equals(reserva.getHora()) &&
                            r.getMesa() != null && reserva.getMesa() != null &&
                            r.getMesa().getId().equals(reserva.getMesa().getId())
            );

            if (duplicada) {
                throw new RuntimeException("Error: La mesa ya se encuentra reservada para está fecha y hora.");
            }
        }

        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> buscarPorFecha(LocalDate fecha) {
        return reservaRepository.findByFecha(fecha);
    }
}
