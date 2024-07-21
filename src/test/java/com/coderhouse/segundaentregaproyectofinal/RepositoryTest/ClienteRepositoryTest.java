package com.coderhouse.segundaentregaproyectofinal.RepositoryTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import com.coderhouse.segundaentregaproyectofinal.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void testFindByNombre() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setDni("37273736");
        cliente.setTelefono("1169675403");
        cliente.setApellido("Cepeda");
        cliente.setDireccion("Calle falsa");
        cliente.setEmail("email@hotmail.com");
        clienteRepository.save(cliente);

        Cliente found = clienteRepository.findByNombre("Juan");
        assertThat(found).isNotNull();
        assertThat(found.getNombre()).isEqualTo("Juan");
    }
}

