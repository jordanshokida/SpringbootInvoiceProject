package com.coderhouse.segundaentregaproyectofinal.ControllerTest;

import com.coderhouse.segundaentregaproyectofinal.controller.ProductoController;
import com.coderhouse.segundaentregaproyectofinal.entity.Producto;
import com.coderhouse.segundaentregaproyectofinal.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void testGetProductoById() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto1");

        when(productoService.obtenerProductoPorId(anyLong())).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.getProductoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
    }

    @Test
    void testGetProductosListByNombre() {
        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Producto1");

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Producto1");

        List<Producto> productos = Arrays.asList(producto1, producto2);

        when(productoService.obtenerProductosPorNombre("Producto1")).thenReturn(productos);

        ResponseEntity<List<Producto>> response = productoController.getProductosListByNombre("Producto1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productos, response.getBody());
    }

    // Continúa con el resto de los métodos
}

