package com.coderhouse.segundaentregaproyectofinal.repository;

import com.coderhouse.segundaentregaproyectofinal.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
