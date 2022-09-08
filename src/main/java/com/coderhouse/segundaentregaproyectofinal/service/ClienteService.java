package com.coderhouse.segundaentregaproyectofinal.service;

import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import com.coderhouse.segundaentregaproyectofinal.entity.Factura;

import java.util.List;


    public interface ClienteService {

        Cliente crearCliente(Cliente cliente);

        void modificarTelefonoCliente(Long id, String tel);

        void borrarCliente(Long id);

        Cliente obtenerClientePorId(Long id);

        List<Cliente> obtenerTodosLosClientes();

        Cliente buscarOCrearCliente(Cliente cliente);

        Cliente obtenerClientePorNombre(String nombre);

        List<Factura> obtenerFacturasPorIdCliente(Long id);
    }


