package com.coderhouse.segundaentregaproyectofinal.ControllerTest;

import com.coderhouse.segundaentregaproyectofinal.controller.DetalleController;
import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;
import com.coderhouse.segundaentregaproyectofinal.service.DetalleService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetalleControllerTest {

    @Mock
    private DetalleService detalleService;

    @InjectMocks
    private DetalleController detalleController;

    @Test
    void testGetItem() {
        Detalle detalle = new Detalle();
        detalle.setId(1L);

        when(detalleService.obtenerDetallePorId(anyLong())).thenReturn(detalle);

        ResponseEntity<Detalle> response = detalleController.getItem(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(detalle, response.getBody());
    }

    @Test
    void testGetAllItems() {
        Detalle detalle1 = new Detalle();
        detalle1.setId(1L);

        Detalle detalle2 = new Detalle();
        detalle2.setId(2L);

        List<Detalle> detalles = Arrays.asList(detalle1, detalle2);

        when(detalleService.obtenerTodosLosDetalles()).thenReturn(detalles);

        ResponseEntity<List<Detalle>> response = detalleController.getAllItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(detalles, response.getBody());
    }

    @Test
    void testSetItem() {
        Detalle detalle = new Detalle();
        detalle.setDescripcion("Detalle1");

        when(detalleService.crearDetalle(detalle)).thenReturn(detalle);

        ResponseEntity<Detalle> response = detalleController.setItem(detalle);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(detalle, response.getBody());
    }

    @Test
    void testUpdateCantidadItem() {
        doNothing().when(detalleService).modificarCantidadDetallePorId(anyLong(), anyInt());

        detalleController.updateCantidadItem(1L, 5);

        // No assertEquals here as the method returns void
    }

    @Test
    void testDeleteItem() {
        doNothing().when(detalleService).borrarDetalle(anyLong());

        detalleController.deleteItem(1L);

        // No assertEquals here as the method returns void
    }
}


