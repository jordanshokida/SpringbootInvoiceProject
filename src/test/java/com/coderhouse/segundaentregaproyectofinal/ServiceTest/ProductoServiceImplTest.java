package com.coderhouse.segundaentregaproyectofinal.ServiceTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Producto;
import com.coderhouse.segundaentregaproyectofinal.repository.ProductoRepository;
import com.coderhouse.segundaentregaproyectofinal.exception.DbException;
import com.coderhouse.segundaentregaproyectofinal.exception.ResourceNotFoundException;
import com.coderhouse.segundaentregaproyectofinal.service.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void testSumarStockProductos() {
        Producto producto = new Producto();
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(100.0);
        producto.setCantidadEnStock(10);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.sumarStockProductos(1L, 5);

        assertEquals(15, producto.getCantidadEnStock());
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(producto);
    }

   /* @Test
    void testSumarStockProductos_NegativeCantidad() {

        assertThrows(DbException.class, () -> productoService.sumarStockProductos(4L, -5));
    }*/

    @Test
    void testSumarStockProductos_ProductoNotFound() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productoService.sumarStockProductos(1L, 5));
    }

    @Test
    void testAgregarProducto_NuevoProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(100.0);
        producto.setCantidadEnStock(10);

        when(productoRepository.findByNombre("Producto1")).thenReturn(Optional.empty());
        when(productoRepository.save(producto)).thenReturn(producto);

        productoService.agregarProducto(producto);

        verify(productoRepository, times(1)).findByNombre("Producto1");
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testAgregarProducto_ProductoExistente() {
        Producto productoExistente = new Producto();
        productoExistente.setNombre("Producto1");
        productoExistente.setDescripcion("Descripcion1");
        productoExistente.setPrecio(50.0);
        productoExistente.setCantidadEnStock(5);

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Producto1");
        nuevoProducto.setDescripcion("Descripcion1");
        nuevoProducto.setPrecio(50.0);
        nuevoProducto.setCantidadEnStock(10);

        when(productoRepository.findByNombre("Producto1")).thenReturn(Optional.of(productoExistente));

        productoService.agregarProducto(nuevoProducto);

        assertEquals(15, productoExistente.getCantidadEnStock());
        verify(productoRepository, times(1)).findByNombre("Producto1");
        verify(productoRepository, times(1)).save(productoExistente);
    }

    /*@Test
    void testModificarPrecioProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(50.0);
        producto.setCantidadEnStock(10);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.modificarPrecioProducto(1L, 100.0);

        assertEquals(100.0, producto.getPrecio());
        verify(productoRepository, times(1)).save(producto);
    }*/

    /*@Test
    void testModificarPrecioProducto_NegativePrecio() {
        assertThrows(DbException.class, () -> productoService.modificarPrecioProducto(1L, -100.0));
    }*/

    @Test
    void testBorrarProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(50.0);
        producto.setCantidadEnStock(10);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.borrarProducto(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testBorrarProducto_NotFound() {
        when(productoRepository.findById(1L)).thenThrow(new DbException("No existe"));

        assertThrows(DbException.class, () -> productoService.borrarProducto(1L));
    }

    /*@Test
    void testObtenerProductoPorId() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(50.0);
        producto.setCantidadEnStock(10);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productoService.obtenerProductoPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Producto1", result.getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }*/

    @Test
    void testObtenerProductoPorId_NotFound() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productoService.obtenerProductoPorId(1L));
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testRestarStockProductos() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(50.0);
        producto.setCantidadEnStock(10);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.restarStockProductos(1L, 5);

        assertEquals(5, producto.getCantidadEnStock());
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testRestarStockProductos_NotEnoughStock() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto1");
        producto.setDescripcion("Descripcion1");
        producto.setPrecio(50.0);
        producto.setCantidadEnStock(5);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        assertThrows(DbException.class, () -> productoService.restarStockProductos(1L, 10));
    }

    @Test
    void testObtenerTodosLosProductos() {
        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Producto1");
        producto1.setDescripcion("Descripcion1");
        producto1.setPrecio(50.0);
        producto1.setCantidadEnStock(10);

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Producto2");
        producto2.setDescripcion("Descripcion2");
        producto2.setPrecio(100.0);
        producto2.setCantidadEnStock(20);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> productos = productoService.obtenerTodosLosProductos();

        assertEquals(2, productos.size());
        assertTrue(productos.contains(producto1));
        assertTrue(productos.contains(producto2));
    }

    @Test
    void testObtenerProductosPorNombre() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto1");
        producto1.setDescripcion("Descripcion1");
        producto1.setPrecio(50.0);
        producto1.setCantidadEnStock(10);

        when(productoRepository.findAllByNombre("Producto1")).thenReturn(Arrays.asList(producto1));

        List<Producto> productos = productoService.obtenerProductosPorNombre("Producto1");

        assertEquals(1, productos.size());
        assertTrue(productos.contains(producto1));
    }

    @Test
    void testObtenerProductoPorNombreYDescripcion() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto1");
        producto1.setDescripcion("Descripcion1");
        producto1.setPrecio(50.0);
        producto1.setCantidadEnStock(10);

        Producto producto2 = new Producto();
        producto2.setNombre("Producto1");
        producto2.setDescripcion("Descripcion2");
        producto2.setPrecio(100.0);
        producto2.setCantidadEnStock(20);

        when(productoRepository.findAllByNombre("Producto1")).thenReturn(Arrays.asList(producto1, producto2));

        Producto result = productoService.obtenerProductoPorNombreYDescripcion("Producto1", "Descripcion1");

        assertNotNull(result);
        assertEquals("Descripcion1", result.getDescripcion());
    }

    @Test
    void testObtenerProductoPorNombreYDescripcion_NotFound() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto1");
        producto1.setDescripcion("Descripcion1");
        producto1.setPrecio(50.0);
        producto1.setCantidadEnStock(10);

        when(productoRepository.findAllByNombre("Producto1")).thenReturn(Arrays.asList(producto1));

        assertThrows(DbException.class, () -> productoService.obtenerProductoPorNombreYDescripcion("Producto1", "Descripcion3"));
    }
}

