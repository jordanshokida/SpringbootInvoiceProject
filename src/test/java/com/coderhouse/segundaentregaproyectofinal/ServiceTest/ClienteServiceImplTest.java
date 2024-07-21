package com.coderhouse.segundaentregaproyectofinal.ServiceTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import com.coderhouse.segundaentregaproyectofinal.repository.ClienteRepository;
import com.coderhouse.segundaentregaproyectofinal.service.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setDni("37273736");
        cliente.setTelefono("1169675403");
        cliente.setApellido("Cepeda");
        cliente.setDireccion("Calle falsa");
        cliente.setEmail("email@hotmail.com");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.crearCliente(cliente);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan", result.getNombre());
        assertEquals("37273736", result.getDni());
        assertEquals("1169675403", result.getTelefono());
        assertEquals("Cepeda", result.getApellido());
        assertEquals("Calle falsa", result.getDireccion());
        assertEquals("email@hotmail.com", result.getEmail());

        verify(clienteRepository, times(1)).save(cliente);
    }

    /*@Test
    void testObtenerClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setDni("37273736");
        cliente.setTelefono("1169675403");
        cliente.setApellido("Cepeda");
        cliente.setDireccion("Calle falsa");
        cliente.setEmail("email@hotmail.com");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.obtenerClientePorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan", result.getNombre());
        assertEquals("37273736", result.getDni());
        assertEquals("1169675403", result.getTelefono());
        assertEquals("Cepeda", result.getApellido());
        assertEquals("Calle falsa", result.getDireccion());
        assertEquals("email@hotmail.com", result.getEmail());
        verify(clienteRepository, times(1)).findById(1L);
    }*/

}

