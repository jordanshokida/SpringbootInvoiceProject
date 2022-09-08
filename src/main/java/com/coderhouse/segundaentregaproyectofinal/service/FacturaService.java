package com.coderhouse.segundaentregaproyectofinal.service;

import com.coderhouse.segundaentregaproyectofinal.dto.DetalleDto;
import com.coderhouse.segundaentregaproyectofinal.dto.FacturaDto;
import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;
import com.coderhouse.segundaentregaproyectofinal.entity.Factura;

import java.util.List;

public interface FacturaService {

    void borrarFactura(Long id);

    Factura facturarCompra(FacturaDto compra);

    Factura obtenerFacturaPorId(Long id) ;

    List<Factura> obtenerTodasLasFacturas();
}
