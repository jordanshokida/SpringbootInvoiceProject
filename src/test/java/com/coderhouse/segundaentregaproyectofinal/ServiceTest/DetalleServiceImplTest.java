package com.coderhouse.segundaentregaproyectofinal.ServiceTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;
import com.coderhouse.segundaentregaproyectofinal.repository.DetalleRepository;
import com.coderhouse.segundaentregaproyectofinal.service.DetalleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DetalleServiceImplTest {

    @Mock
    private DetalleRepository detalleRepository;

    @InjectMocks
    private DetalleServiceImpl detalleService;

    @Test
    void testCrearDetalle() {
        Detalle detalle = new Detalle();
        detalle.setId(1L);
        detalle.setNombreProducto("Producto1");
        detalle.setDescripcion("Descripcion");

        when(detalleRepository.save(detalle)).thenReturn(detalle);

        Detalle result = detalleService.crearDetalle(detalle);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Producto1", result.getNombreProducto());
        verify(detalleRepository, times(1)).save(detalle);
    }
    @Test
    void testObtenerDetallePorId() {
        Detalle detalle = new Detalle();
        detalle.setId(1L);
        detalle.setNombreProducto("Producto1");
        detalle.setDescripcion("Descripcion");

        when(detalleRepository.findById(1L)).thenReturn(Optional.of(detalle));

        Detalle result = detalleService.obtenerDetallePorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Producto1", result.getNombreProducto());
        verify(detalleRepository, times(1)).findById(1L);
    }

}

