package com.ejemplo.restaurante.controller;

import com.ejemplo.restaurante.dto.ReservaRequestDTO;
import com.ejemplo.restaurante.entity.Reserva;
import com.ejemplo.restaurante.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController{

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> obtenerTodas() {
        return reservaService.listarReservas() ;
    }

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody ReservaRequestDTO dto) {
        Reserva nuevaReserva = reservaService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.obtenerPorId(id);
        return ResponseEntity.ok(reserva);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaRequestDTO dto) {
        Reserva actualizada = reservaService.actualizar(id, dto);
                    return ResponseEntity.ok(actualizada);
    }


    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Map<String, String>> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "La reserva fue cancelada con éxito");
        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("/confirmadas")
    public List<Reserva> obtenerReservasConfirmadas(){
        return reservaService.listarReservas().stream()
                .filter(r -> "CONFIRMADA".equalsIgnoreCase(r.getEstado()))
                .toList();
    }

    @GetMapping ("/disponibilidad")
    public ResponseEntity<String> verificarDisponibilidadGeneral() {
        long totalReservasActivas = reservaService.listarReservas().stream()
                .filter(r -> !"CANCELADA".equalsIgnoreCase(r.getEstado()))
                .count();
        return ResponseEntity.ok("Actualmente hay " + totalReservasActivas + "reservas activas en el sistema.");
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id){
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
