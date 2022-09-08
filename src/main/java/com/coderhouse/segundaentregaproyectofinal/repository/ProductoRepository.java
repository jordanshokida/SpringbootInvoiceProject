package com.coderhouse.segundaentregaproyectofinal.repository;

import com.coderhouse.segundaentregaproyectofinal.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByNombre(String nombre);
    Optional<Producto> findByNombre(String nombre);
}
