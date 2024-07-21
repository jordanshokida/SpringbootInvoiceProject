package com.coderhouse.segundaentregaproyectofinal.ControllerTest;

import com.coderhouse.segundaentregaproyectofinal.controller.FacturaController;
import com.coderhouse.segundaentregaproyectofinal.dto.FacturaDto;
import com.coderhouse.segundaentregaproyectofinal.entity.Factura;
import com.coderhouse.segundaentregaproyectofinal.service.FacturaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

    @Test
    void testFacturarCompraDto() {
        FacturaDto compraDto = new FacturaDto();
        Factura factura = new Factura();

        when(facturaService.facturarCompra(compraDto)).thenReturn(factura);

        Factura response = facturaController.facturarCompraDto(compraDto);

        assertEquals(factura, response);
    }

    @Test
    void testGetAllFacturas() {
        List<Factura> facturas = List.of(new Factura());

        when(facturaService.obtenerTodasLasFacturas()).thenReturn(facturas);

        ResponseEntity<List<Factura>> response = facturaController.getAllFacturas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facturas, response.getBody());
    }

    @Test
    void testGetFacturaById() {
        Factura factura = new Factura();
        factura.setId(1L);

        when(facturaService.obtenerFacturaPorId(anyLong())).thenReturn(factura);

        ResponseEntity<Factura> response = facturaController.getFacturaById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(factura, response.getBody());
    }
}

