package com.coderhouse.segundaentregaproyectofinal.repository;

import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import com.coderhouse.segundaentregaproyectofinal.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByNombre(String nombre);

}
