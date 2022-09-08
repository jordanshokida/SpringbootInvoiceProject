package com.coderhouse.segundaentregaproyectofinal.service;

import com.coderhouse.segundaentregaproyectofinal.entity.Empresa;

import java.util.List;

public interface EmpresaService {

        Empresa crearEmpresa(Empresa empresa);

        void borrarEmpresaPorId(Long id);

        Empresa obtenerEmpresaPorId(Long id);

        List<Empresa> obtenerTodasLasEmpresas();

}
