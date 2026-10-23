package com.ejemplo.restaurante.repository;

import com.ejemplo.restaurante.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {



    Optional<Mesa> findByNumeroMesa(Integer numeroMesa);



    List<Mesa> findByActivaTrue();
}
