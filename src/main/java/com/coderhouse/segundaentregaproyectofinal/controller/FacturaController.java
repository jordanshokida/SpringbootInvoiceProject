package com.coderhouse.segundaentregaproyectofinal.controller;

import com.coderhouse.segundaentregaproyectofinal.dto.FacturaDto;
import com.coderhouse.segundaentregaproyectofinal.entity.Factura;
import com.coderhouse.segundaentregaproyectofinal.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @PostMapping("/facturarCompraDto")
    public Factura facturarCompraDto(@RequestBody FacturaDto compra) {
        return facturaService.facturarCompra(compra);
    }

    @GetMapping("/getFacturasList")
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturasList = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok().body(facturasList);
    }

    @GetMapping("/getFacturaById/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable(value = "id") Long facturaId) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        return ResponseEntity.ok().body(factura);
    }

}