package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.entity.Mesa;
import com.ejemplo.restaurante.exception.RecursoNoEncontradoException;
import com.ejemplo.restaurante.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
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
        return mesaRepository.save(mesa);
    }



    public Mesa actualizar(Long id, com.ejemplo.restaurante.dto.MesaRequestDTO dto) {
        Mesa mesaExistente = buscarPorId(id);
        mesaExistente.setNumeroMesa(dto.getNumeroMesa());
        mesaExistente.setCapacidad(dto.getCapacidad());
        mesaExistente.setUbicacion(dto.getUbicacion());
        return mesaRepository.save(mesaExistente);
    }


    public void eliminar(Long id) {
        Mesa mesa = buscarPorId(id);
        mesaRepository.delete(mesa);
    }
}
