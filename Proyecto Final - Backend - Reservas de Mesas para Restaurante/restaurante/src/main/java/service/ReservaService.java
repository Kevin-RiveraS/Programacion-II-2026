package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.dto.ReservaRequestDTO;
import com.ejemplo.restaurante.entity.Mesa;
import com.ejemplo.restaurante.entity.Reserva;
import com.ejemplo.restaurante.exception.RecursoNoEncontradoException;
import com.ejemplo.restaurante.repository.MesaRepository;
import com.ejemplo.restaurante.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }


    public Reserva registrar(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setFecha(dto.getFecha());
        reserva.setHora(dto.getHora());
        reserva.setCantidadPersonas(dto.getCantidadPersonas());
        reserva.setNombreCliente(dto.getNombreCliente());
        reserva.setTelefonoCliente(dto.getTelefonoCliente());


        Mesa mesaTemp = new Mesa();
        mesaTemp.setId(dto.getMesaId());
        reserva.setMesa(mesaTemp);


        return guardarReserva(reserva);
    }


    public Reserva actualizar(Long id, ReservaRequestDTO dto) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Registro no encontrado con ID: " + id));

        reservaExistente.setFecha(dto.getFecha());
        reservaExistente.setHora(dto.getHora());
        reservaExistente.setCantidadPersonas(dto.getCantidadPersonas());
        reservaExistente.setNombreCliente(dto.getNombreCliente());
        reservaExistente.setTelefonoCliente(dto.getTelefonoCliente());

        Mesa mesaTemp = new Mesa();
        mesaTemp.setId(dto.getMesaId());
        reservaExistente.setMesa(mesaTemp);


        return guardarReserva(reservaExistente);
    }


    public Reserva guardarReserva(Reserva reserva) {

        if (reserva.getMesa() == null || reserva.getMesa().getId() == null) {
            throw new RuntimeException("Error: La reserva debe estar asociada a una mesa.");
        }

        Mesa mesa = mesaRepository.findById(reserva.getMesa().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Error: La mesa seleccionada no existe."));

        if (mesa.getActiva() == null || !mesa.getActiva()) {
            throw new RuntimeException("Error: La mesa seleccionada no se encuentra activa para recibir reservas.");
        }

        if (reserva.getCantidadPersonas() == null || reserva.getCantidadPersonas() <= 0) {
            throw new RuntimeException("Error: La cantidad de personas debe ser mayor a cero.");
        }

        if (reserva.getCantidadPersonas() > mesa.getCapacidad()) {
            throw new RuntimeException("Error: La cantidad de personas (" + reserva.getCantidadPersonas() + ") " +
                    "excede la capacidad máxima de la mesa (" + mesa.getCapacidad() + ").");
        }

        boolean horaOcupada;

        if (reserva.getId() !=null){
            horaOcupada =reservaRepository.existsByMesaIdAndFechaAndHoraAndEstadoNotAndIdNot(
                    mesa.getId(),
                    reserva.getFecha(),
                    reserva.getHora(),
                    "CANCELADA",
                    reserva.getId()
            );
        }else{
            horaOcupada =reservaRepository.existsByMesaIdAndFechaAndHoraAndEstadoNot(
                    mesa.getId(),
                    reserva.getFecha(),
                    reserva.getHora(),
                    "CANCELADA"
            );
        }
        if (horaOcupada) {
            throw new RuntimeException("Error: La mesa ya se encuentra reservada para esta fecha y hora.");
        }

        reserva.setMesa(mesa);
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> buscarPorFecha(LocalDate fecha) {
        return reservaRepository.findByFecha(fecha);
    }

    public Reserva obtenerPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrado con ID: " + id));
    }

    public void eliminarReserva(Long id){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("No se puede eliminar. Reserva no encontrada con ID: "+id));

        reservaRepository.delete(reserva);
    }

    public void cancelarReserva(long id){
        Reserva reserva =obtenerPorId(id);
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }
}
