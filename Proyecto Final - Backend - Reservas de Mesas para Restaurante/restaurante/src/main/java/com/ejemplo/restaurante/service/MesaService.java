package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.entity.Mesa;
import com.ejemplo.restaurante.exception.RecursoNoEncontradoException;
import com.ejemplo.restaurante.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

public List<Mesa> listarMesasDisponibles(LocalDate fecha, LocalTime hora){
        return mesaRepository.findMesasDisponibles(fecha, hora);
    }


    public List<Mesa> listar() {
        return mesaRepository.findAll();
    }


    public Mesa buscarPorId(Long id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Registro no encontrado con ID: " + id));
    }


    public Mesa registrar(com.ejemplo.restaurante.dto.MesaRequestDTO dto) {
        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(dto.getNumeroMesa());
        mesa.setCapacidad(dto.getCapacidad());
        mesa.setUbicacion(dto.getUbicacion());
        mesa.setActiva(dto.getActiva());
        return mesaRepository.save(mesa);
    }



    public Mesa actualizar(Long id, com.ejemplo.restaurante.dto.MesaRequestDTO dto) {
        Mesa mesaExistente = buscarPorId(id);
        mesaExistente.setNumeroMesa(dto.getNumeroMesa());
        mesaExistente.setCapacidad(dto.getCapacidad());
        mesaExistente.setUbicacion(dto.getUbicacion());
        mesaExistente.setActiva(dto.getActiva());
        return mesaRepository.save(mesaExistente);
    }

    public Mesa actualizarParcial(Long id, com.ejemplo.restaurante.dto.MesaRequestDTO dto) {
        Mesa mesaExistente = buscarPorId(id);

        if (dto.getNumeroMesa() != null) {
            mesaExistente.setNumeroMesa(dto.getNumeroMesa());
        }
        if (dto.getCapacidad() != null) {
            mesaExistente.setCapacidad(dto.getCapacidad());
        }
        if (dto.getUbicacion() != null) {
            mesaExistente.setUbicacion(dto.getUbicacion());
        }
        if (dto.getActiva() != null) {
            mesaExistente.setActiva(dto.getActiva());
        }
        return mesaRepository.save(mesaExistente);
    }


    public void eliminar(Long id) {
        Mesa mesa = buscarPorId(id);
        mesaRepository.delete(mesa);
    }
}
