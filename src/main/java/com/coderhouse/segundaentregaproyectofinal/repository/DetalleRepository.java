package com.coderhouse.segundaentregaproyectofinal.repository;


import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {
      Optional<Detalle> findById(Long id);
}
