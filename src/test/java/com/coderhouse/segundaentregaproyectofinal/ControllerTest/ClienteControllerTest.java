package com.coderhouse.segundaentregaproyectofinal.ControllerTest;

import com.coderhouse.segundaentregaproyectofinal.controller.ClienteController;
import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import com.coderhouse.segundaentregaproyectofinal.service.ClienteService;
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
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void testGetCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteService.obtenerClientePorId(anyLong())).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.getCliente(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteService.obtenerTodosLosClientes()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> response = clienteController.getAllClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    void testSetCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Cliente1");

        when(clienteService.crearCliente(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.setCliente(cliente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }
}

