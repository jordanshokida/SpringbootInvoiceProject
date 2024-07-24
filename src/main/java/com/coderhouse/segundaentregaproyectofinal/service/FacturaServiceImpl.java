package com.coderhouse.segundaentregaproyectofinal.service;


import com.coderhouse.segundaentregaproyectofinal.dto.DetalleDto;
import com.coderhouse.segundaentregaproyectofinal.dto.FacturaDto;
import com.coderhouse.segundaentregaproyectofinal.entity.*;
import com.coderhouse.segundaentregaproyectofinal.exception.DbException;
import com.coderhouse.segundaentregaproyectofinal.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class FacturaServiceImpl implements FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    ProductoService productoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EmpresaService empresaService;


    public void borrarFactura(Long id) {
        Factura factura = obtenerFacturaPorId(id);
        facturaRepository.deleteById(factura.getId());
        log.info("Se va a borrar la factura {}", factura.getId());
    }

    public Factura obtenerFacturaPorId(Long id) {

        Optional<Factura> factura = facturaRepository.findById(id);

        if (factura.isPresent()) {

            return factura.get();

        } else {

            throw new DbException("La factura con el id :" + id + " no existe");
        }

    }

    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    public Factura facturarCompra(FacturaDto compra) {

        Cliente cliente = clienteService.buscarOCrearCliente(compra.getCliente());


        Factura factura = new Factura();
        Date date = new Date();
        factura.setCliente(cliente);
        factura.setFecha(date);
        Empresa empresa = empresaService.obtenerEmpresaPorId(compra.getIdEmpresa());

        factura.setEmpresa(empresa);

        factura.setDetalle(new HashSet<>());

        double totalCompra = 0;

        List<Producto> productosLista = new ArrayList<>();

        for (DetalleDto c : compra.getDetalleDtos()) {

            Producto miProducto = productoService.obtenerProductoPorNombreYDescripcion(c.getNombreProducto(), c.getDescripcionProducto());
            productosLista.add(miProducto);
            int cantidad = c.getCantidad();
            double precio = miProducto.getPrecio();
            double totalParcial = precio * cantidad;
            productoService.restarStockProductos(miProducto.getId(), cantidad);
            totalCompra += totalParcial;
            Detalle detalle = crearDetalle(c.getNombreProducto(), c.getDescripcionProducto(), cantidad, totalParcial);
            factura.agregarDetalle(detalle);
            factura.setProductos(productosLista);
        }

        factura.setMontoTotal(totalCompra);

        Factura miFactura = facturaRepository.save(factura);
        log.error("Información de factura {}", miFactura);

        return miFactura;

    }



    public Detalle crearDetalle(String nombreProducto, String descripcionProducto, int cantidad, double totalParcial){

            Detalle detalleAGuardar = new Detalle();

            detalleAGuardar.setNombreProducto(nombreProducto);
            detalleAGuardar.setDescripcion(descripcionProducto);
            detalleAGuardar.setCantidad(cantidad);
            detalleAGuardar.setTotalParcial(totalParcial);
            return detalleAGuardar;
        }

}