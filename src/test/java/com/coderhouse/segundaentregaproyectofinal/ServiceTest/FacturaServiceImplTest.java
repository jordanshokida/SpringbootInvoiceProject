package com.coderhouse.segundaentregaproyectofinal.ServiceTest;

import com.coderhouse.segundaentregaproyectofinal.dto.DetalleDto;
import com.coderhouse.segundaentregaproyectofinal.dto.FacturaDto;
import com.coderhouse.segundaentregaproyectofinal.entity.*;
import com.coderhouse.segundaentregaproyectofinal.expcetion.DbException;
import com.coderhouse.segundaentregaproyectofinal.repository.FacturaRepository;
import com.coderhouse.segundaentregaproyectofinal.service.ClienteService;
import com.coderhouse.segundaentregaproyectofinal.service.EmpresaService;
import com.coderhouse.segundaentregaproyectofinal.service.ProductoService;
import com.coderhouse.segundaentregaproyectofinal.service.FacturaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturaServiceImplTest {
    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private ProductoService productoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    @Test
    void testBorrarFactura() {
        Factura factura = new Factura();
        factura.setId(1L);

        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        facturaService.borrarFactura(1L);

        verify(facturaRepository, times(1)).deleteById(1L);
        verify(facturaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerFacturaPorId() {
        Factura factura = new Factura();
        factura.setId(1L);

        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        Factura result = facturaService.obtenerFacturaPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(facturaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerFacturaPorId_NotFound() {
        when(facturaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DbException.class, () -> facturaService.obtenerFacturaPorId(1L));
        verify(facturaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodasLasFacturas() {
        List<Factura> facturas = new ArrayList<>();
        facturas.add(new Factura());

        when(facturaRepository.findAll()).thenReturn(facturas);

        List<Factura> result = facturaService.obtenerTodasLasFacturas();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    void testFacturarCompra() {
        FacturaDto facturaDto = new FacturaDto();
        facturaDto.setIdEmpresa(1L);
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        facturaDto.setCliente(cliente);

        DetalleDto detalleDto = new DetalleDto();
        detalleDto.setNombreProducto("Producto1");
        detalleDto.setDescripcionProducto("Descripcion1");
        detalleDto.setCantidad(2);
        facturaDto.setDetalleDtos(Collections.singletonList(detalleDto));

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(10.0);

        Empresa empresa = new Empresa();
        empresa.setId(1L);

        when(clienteService.buscarOCrearCliente(cliente)).thenReturn(cliente);
        when(empresaService.obtenerEmpresaPorId(1L)).thenReturn(empresa);
        when(productoService.obtenerProductoPorNombreYDescripcion("Producto1", "Descripcion1")).thenReturn(producto);
        when(facturaRepository.save(any(Factura.class))).thenReturn(new Factura());

        Factura result = facturaService.facturarCompra(facturaDto);

        assertNotNull(result);
        verify(clienteService, times(1)).buscarOCrearCliente(cliente);
        verify(empresaService, times(1)).obtenerEmpresaPorId(1L);
        verify(productoService, times(1)).obtenerProductoPorNombreYDescripcion("Producto1", "Descripcion1");
        verify(productoService, times(1)).restarStockProductos(1L, 2);
        verify(facturaRepository, times(1)).save(any(Factura.class));
    }

    @Test
    void testCrearDetalle() {
        String nombreProducto = "Producto1";
        String descripcionProducto = "Descripcion1";
        int cantidad = 2;
        double totalParcial = 20.0;

        Detalle detalle = facturaService.crearDetalle(nombreProducto, descripcionProducto, cantidad, totalParcial);

        assertNotNull(detalle);
        assertEquals(nombreProducto, detalle.getNombreProducto());
        assertEquals(descripcionProducto, detalle.getDescripcion());
        assertEquals(cantidad, detalle.getCantidad());
        assertEquals(totalParcial, detalle.getTotalParcial());
    }

}

