package com.coderhouse.segundaentregaproyectofinal.RepositoryTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Producto;
import com.coderhouse.segundaentregaproyectofinal.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void testFindAllByNombre() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto1");
        producto1.setDescripcion("Descripcion1");
        producto1.setPrecio(50.0);
        producto1.setCantidadEnStock(10);
        productoRepository.save(producto1);

        List<Producto> found = productoRepository.findAllByNombre("Producto1");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getNombre()).isEqualTo("Producto1");
    }

    @Test
    void testFindByNombre() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto1");
        producto1.setDescripcion("Descripcion1");
        producto1.setPrecio(50.0);
        producto1.setCantidadEnStock(10);
        productoRepository.save(producto1);

        Optional<Producto> found = productoRepository.findByNombre("Producto1");
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Producto1");
    }
}

