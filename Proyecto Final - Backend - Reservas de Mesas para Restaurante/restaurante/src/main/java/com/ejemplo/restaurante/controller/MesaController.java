package com.ejemplo.restaurante.controller;

import com.ejemplo.restaurante.dto.MensajeRespuestaDTO;
import com.ejemplo.restaurante.dto.MesaRequestDTO;
import com.ejemplo.restaurante.entity.Mesa;
import com.ejemplo.restaurante.service.MesaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    private final MesaService mesaService;



    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public ResponseEntity<MensajeRespuestaDTO> listar() {
        List<Mesa> mesas = mesaService.listar();
        MensajeRespuestaDTO respuesta =new MensajeRespuestaDTO("¡Lista de mesas obtenida con éxito!", mesas);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeRespuestaDTO> buscarPorId(@PathVariable Long id) {
        Mesa mesa = mesaService.buscarPorId(id);
        MensajeRespuestaDTO respuesta = new MensajeRespuestaDTO("¡Mesa encontrada exitosamente!", mesa);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<MensajeRespuestaDTO> registrar(@Valid @RequestBody MesaRequestDTO dto) {
        Mesa nuevaMesa = mesaService.registrar(dto);
        MensajeRespuestaDTO respuesta = new MensajeRespuestaDTO("¡Mesa registrada con éxito!", nuevaMesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeRespuestaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MesaRequestDTO dto) {
        Mesa mesaActualizada = mesaService.actualizar(id, dto);
        MensajeRespuestaDTO respuesta =new MensajeRespuestaDTO("¡Mesa actualizada correctamente!", mesaActualizada);
        return ResponseEntity.ok(respuesta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MensajeRespuestaDTO> actualizarParcial(@PathVariable Long id, @RequestBody MesaRequestDTO dto){
        Mesa mesaActualizada =mesaService.actualizarParcial(id, dto);
        MensajeRespuestaDTO respuesta = new MensajeRespuestaDTO("¡Actualización parcial exitosa!", mesaActualizada);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
        return ResponseEntity.ok(java.util.Collections.singletonMap("mensaje", "¡Mesa eliminada correctamente!"));
    }



    @GetMapping("/disponibles")
    public ResponseEntity<MensajeRespuestaDTO> listarMesasDisponibles(
            @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate fecha,
            @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.TIME) java.time.LocalTime hora) {

        List<Mesa> mesasDisponibles = mesaService.listarMesasDisponibles(fecha, hora);
        MensajeRespuestaDTO respuesta =new MensajeRespuestaDTO("¡Listado de mesas disponibles obtenido correctamente!", mesasDisponibles);
        return ResponseEntity.ok(respuesta);
    }
}

