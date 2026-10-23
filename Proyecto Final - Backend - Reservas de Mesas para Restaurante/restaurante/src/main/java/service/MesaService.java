package com.ejemplo.restaurante.service;


import com.ejemplo.restaurante.entity.Mesa;
import com.ejemplo.restaurante.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Mesa guardarMesa(Mesa mesa) {
        if (mesa.getCapacidad() <=0){
            throw new RuntimeException("Error: La capacidad de la mesa debe ser mayor a cero.");
        }
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> buscarPorId(Long id) {
        return mesaRepository.findById(id);
    }
}

