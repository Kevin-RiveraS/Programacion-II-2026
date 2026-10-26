package com.ejemplo.restaurante.controller;

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
public class MesaController {

    private final MesaService mesaService;



    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> listar() {
        List<Mesa> mesas = mesaService.listar();
        return ResponseEntity.ok(mesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarPorId(@PathVariable Long id) {
        Mesa mesa = mesaService.buscarPorId(id);
        return ResponseEntity.ok(mesa);
    }

    @PostMapping
    public ResponseEntity<Mesa> registrar(@Valid @RequestBody MesaRequestDTO dto) {
        Mesa nuevaMesa = mesaService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> actualizar(@PathVariable Long id, @Valid @RequestBody MesaRequestDTO dto) {
        Mesa mesaActualizada = mesaService.actualizar(id, dto);
        return ResponseEntity.ok(mesaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
