package com.ejemplo.restaurante.controller;

import com.ejemplo.restaurante.dto.ReservaActualizarDTO;
import com.ejemplo.restaurante.dto.ReservaRequestDTO;
import com.ejemplo.restaurante.dto.ReservaResponseDTO;
import com.ejemplo.restaurante.dto.OcupacionResponseDTO;
import com.ejemplo.restaurante.entity.Mesa;
import com.ejemplo.restaurante.entity.Reserva;
import com.ejemplo.restaurante.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    private ReservaResponseDTO convertirADTO(Reserva r) {
        Integer numMesa = (r.getMesa() != null) ? r.getMesa().getNumeroMesa() : null;
        return new ReservaResponseDTO(r.getId(), r.getFecha(), r.getHora(), r.getEstado(), numMesa, r.getCantidadPersonas(), r.getNombreCliente(), r.getTelefonoCliente());
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerTodas() {
        List<ReservaResponseDTO> lista = reservaService.listarReservas().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡Lista de reservas obtenida con éxito!");
        respuesta.put("datos", lista);

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearReserva(@Valid @RequestBody ReservaRequestDTO dto) {
        Reserva nuevaReserva = reservaService.registrar(dto);
        ReservaResponseDTO dtoRespuesta = convertirADTO(nuevaReserva);

        Map<String, Object> respuesta= new LinkedHashMap<>();
        respuesta.put("mensaje", "¡La reserva fue creada con éxito!");
        respuesta.put("datos", dtoRespuesta);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.obtenerPorId(id);

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡Reserva obtenida con éxito!");
        respuesta.put("datos", convertirADTO(reserva));
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarReserva(@PathVariable Long id, @Valid @RequestBody ReservaActualizarDTO dto) {
        Reserva actualizada = reservaService.actualizar(id, dto);
        ReservaResponseDTO dtoRespuesta =convertirADTO(actualizada);

        Map<String, Object> respuesta= new LinkedHashMap<>();
        respuesta.put("mensaje", "¡La reserva fue actualizada con éxito!");
        respuesta.put("datos", dtoRespuesta);
        return ResponseEntity.ok(respuesta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarParcialReserva(@PathVariable Long id, @Valid @RequestBody ReservaActualizarDTO dto) {
        Reserva actualizada = reservaService.actualizarParcial(id, dto);
        ReservaResponseDTO dtoRespuesta =convertirADTO(actualizada);

        Map<String, Object> respuesta= new LinkedHashMap<>();
        respuesta.put("mensaje", "¡La reserva fue actualizada parcialmente con éxito!");
        respuesta.put("datos", dtoRespuesta);
        return ResponseEntity.ok(respuesta);
    }


    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Map<String, Object>> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡La reserva fue cancelada con éxito!");
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/confirmadas")
    public ResponseEntity<Map<String, Object>> obtenerReservasConfirmadas() {
        List<ReservaResponseDTO> lista = reservaService.listarReservas().stream()
                .filter(r -> "CONFIRMADA".equalsIgnoreCase(r.getEstado()))
                .map(this::convertirADTO)
                .collect(Collectors.toList());

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡Reservas confirmadas obtenidas con éxito!");
        respuesta.put("datos", lista);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<Map<String, Object>> verificarDisponibilidadGeneral() {
        long totalReservasActivas = reservaService.listarReservas().stream()
                .filter(r -> !"CANCELADA".equalsIgnoreCase(r.getEstado()))
                .count();

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡Disponibilidad general verificada con éxito!");
        respuesta.put("totalReservasActivas", totalReservasActivas);
        return ResponseEntity.ok(respuesta);
    }


    @GetMapping("/mesas-disponibles")
    public ResponseEntity<Map<String, Object>> obtenerMesasDisponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora) {

        List<Mesa> mesasDisponibles = reservaService.obtenerMesasDisponibles(fecha, hora);

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡Mesas disponibles obtenidas con éxito!");
        respuesta.put("datos", mesasDisponibles);
        return ResponseEntity.ok(respuesta);
    }


    @GetMapping("/por-fecha")
    public ResponseEntity<Map<String, Object>> obtenerReservasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<ReservaResponseDTO> lista= reservaService.buscarPorFecha(fecha).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡Reserva por fecha obtenidas con éxito!");
        respuesta.put("datos", lista);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>>eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡La reserva fue eliminada exitosamente!");

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/ocupacion")
    public ResponseEntity<Map<String, Object>> obtenerOcupacionEstimada(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        Double porcentajeOcupacion = reservaService.calcularOcupacionEstimada(fecha);
        OcupacionResponseDTO response = new OcupacionResponseDTO(fecha.toString(), porcentajeOcupacion);

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("mensaje", "¡Ocupación estimada obtenida con éxito!");
        respuesta.put("datos", response);
        return ResponseEntity.ok(respuesta);
    }
}

