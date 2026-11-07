package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.dto.ReservaActualizarDTO;
import com.ejemplo.restaurante.dto.ReservaRequestDTO;
import com.ejemplo.restaurante.entity.Mesa;
import com.ejemplo.restaurante.entity.Reserva;
import com.ejemplo.restaurante.exception.ConflictException;
import com.ejemplo.restaurante.exception.RecursoNoEncontradoException;
import com.ejemplo.restaurante.repository.MesaRepository;
import com.ejemplo.restaurante.repository.ReservaRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;

    public ReservaService(ReservaRepository reservaRepository, MesaRepository mesaRepository){
        this.reservaRepository = reservaRepository;
        this.mesaRepository = mesaRepository;
    }
    public List<Reserva> listarReservas(){
        return reservaRepository.findAll();
    }


    public Reserva registrar(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setFecha(dto.getFecha());
        reserva.setHora(dto.getHora());
        reserva.setCantidadPersonas(dto.getCantidadPersonas());
        reserva.setNombreCliente(dto.getNombreCliente());
        reserva.setTelefonoCliente(dto.getTelefonoCliente());

        if (dto.getEstado()!= null && !dto.getEstado().isEmpty()){
            reserva.setEstado(dto.getEstado().toUpperCase());
        }

        Mesa mesaTemp = new Mesa();
        mesaTemp.setId(dto.getMesaId());
        reserva.setMesa(mesaTemp);


        return guardarReserva(reserva);
    }


    public Reserva actualizar(Long id, ReservaActualizarDTO dto) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Registro no encontrado con ID: " + id));

        if ("CANCELADA".equalsIgnoreCase(reservaExistente.getEstado())){
            throw new ConflictException("Error: No se puede modificar una reserva que ya se encuentra cancelada.");
        }

        reservaExistente.setFecha(dto.getFecha());
        reservaExistente.setHora(dto.getHora());
        reservaExistente.setCantidadPersonas(dto.getCantidadPersonas());
        reservaExistente.setNombreCliente(dto.getNombreCliente());
        reservaExistente.setTelefonoCliente(dto.getTelefonoCliente());
        reservaExistente.setEstado(dto.getEstado() !=null ? dto.getEstado().toUpperCase(): null);

        Mesa mesaTemp = new Mesa();
        mesaTemp.setId(dto.getMesaId());
        reservaExistente.setMesa(mesaTemp);


        return guardarReserva(reservaExistente);
    }



    public Reserva actualizarParcial(Long id, ReservaActualizarDTO dto) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se puede actualizar. Reserva no encontrada con ID: " + id));

        if ("CANCELADA".equalsIgnoreCase(reservaExistente.getEstado())){
            throw new ConflictException("Error: No se puede modificar una reserva que ya se encuentra cancelada.");
        }

        if (dto.getFecha() != null) {
            reservaExistente.setFecha(dto.getFecha());
        }
        if (dto.getHora() != null) {
            reservaExistente.setHora(dto.getHora());
        }
        if (dto.getCantidadPersonas() != null) {
            reservaExistente.setCantidadPersonas(dto.getCantidadPersonas());
        }
        if (dto.getNombreCliente() != null) {
            reservaExistente.setNombreCliente(dto.getNombreCliente());
        }
        if (dto.getTelefonoCliente() != null) {
            reservaExistente.setTelefonoCliente(dto.getTelefonoCliente());
        }
        if (dto.getMesaId() != null) {
            Mesa mesa = mesaRepository.findById(dto.getMesaId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Error: La mesa seleccionada no existe."));
            reservaExistente.setMesa(mesa);
        }

        if (dto.getEstado() !=null){
            reservaExistente.setEstado(dto.getEstado().toUpperCase());
        }

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

    public List<Reserva> listarReservasConfirmadas(){
        return reservaRepository.findAll().stream()
                .filter(r -> "CONFIRMADA".equalsIgnoreCase(r.getEstado()))
                .collect(Collectors.toList());
    }

    public long contarReservasActivas(){
        return reservaRepository.findAll().stream()
                .filter(r -> !"CANCELADA".equalsIgnoreCase(r.getEstado()))
                .count();
    }

    public List<Mesa> obtenerMesasDisponibles(LocalDate fecha, LocalTime hora) {
        List<Mesa> mesasActivas = mesaRepository.findByActivaTrue();

        return mesasActivas.stream().filter(mesa -> {
            boolean estaReservada = reservaRepository.existsByMesaIdAndFechaAndHoraAndEstadoNot(
                    mesa.getId(), fecha, hora, "CANCELADA"
            );
            return !estaReservada;
        }).collect(java.util.stream.Collectors.toList());
    }


    public Double calcularOcupacionEstimada(LocalDate fecha) {
        long totalMesasActivas = mesaRepository.findByActivaTrue().size();
        if (totalMesasActivas == 0) {
            return 0.0;
        }


        long mesasReservadas = reservaRepository.contarMesasReservadasPorFecha(fecha);


        double porcentaje = ((double) mesasReservadas / totalMesasActivas) * 100.0;
        return Math.round(porcentaje * 100.0) / 100.0;
    }
}
