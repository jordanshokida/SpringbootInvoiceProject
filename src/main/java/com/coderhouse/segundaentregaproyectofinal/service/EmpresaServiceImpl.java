package com.coderhouse.segundaentregaproyectofinal.service;

import com.coderhouse.segundaentregaproyectofinal.entity.Empresa;
import com.coderhouse.segundaentregaproyectofinal.exception.DbException;
import com.coderhouse.segundaentregaproyectofinal.exception.ResourceAlreadyExistsException;
import com.coderhouse.segundaentregaproyectofinal.exception.ResourceNotFoundException;
import com.coderhouse.segundaentregaproyectofinal.repository.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmpresaServiceImpl implements EmpresaService {
    @Autowired
    EmpresaRepository empresaRepostory;

    public Empresa crearEmpresa(Empresa empresa) {
        this.validarString(empresa.getNombre());

        String nombre = empresa.getNombre();
        Optional<Empresa> empresas = this.empresaRepostory.findByNombre(nombre);
        if (empresas.isPresent()) {
            log.info("Empresa ya existente");
            throw new ResourceAlreadyExistsException("La empresa con ese nombre ya existe");
        }

        this.validarString(empresa.getRubro());
        return empresaRepostory.save(empresa);
    }


    private void validarString(String stringAEvaluar) {
        if (stringAEvaluar.isEmpty()) {
            log.info("Se va a verificar si el campo 'nombre' de la empresa está vacío");
            throw new IllegalArgumentException("El campo 'nombre' de la empresa está vacío");
        }
    }

    public void borrarEmpresaPorId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id brindado no puede ser 0 ni negativo");
        }
        try {
            empresaRepostory.deleteById(id);
        } catch (RuntimeException exception) {
            throw new DbException("No existe la empresa con el id " + id);
        }
        Optional<Empresa> empresa = empresaRepostory.findById(id);
        if (!empresa.isPresent()) {
            log.info("La empresa fue eliminada exitosamente, su id es" + id);

        } else {
            log.info("No se pudo eliminar la empresa con el id: " + id);

        }
    }

    public Empresa obtenerEmpresaPorId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id pedido no puede ser 0 ni negativo");
        }

        this.empresaRepostory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La empresa con el ID " + id + " no existe"));
        return this.empresaRepostory.getById(id);
    }

    public List<Empresa> obtenerTodasLasEmpresas() {
        return empresaRepostory.findAll();
    }
}
