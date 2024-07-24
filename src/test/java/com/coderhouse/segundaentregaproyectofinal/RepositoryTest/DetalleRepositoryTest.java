package com.coderhouse.segundaentregaproyectofinal.RepositoryTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;
import com.coderhouse.segundaentregaproyectofinal.repository.DetalleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DetalleRepositoryTest {

    @Autowired
    private DetalleRepository detalleRepository;

    @Test
    void testFindById() {
        Detalle detalle = new Detalle();
        detalle.setId(1L);
        detalle.setNombreProducto("Producto1");
        detalle.setDescripcion("Descripcion");
        detalle.setCantidad(1);
        detalle.setTotalParcial(1);
        detalleRepository.save(detalle);

        Optional<Detalle> found = detalleRepository.findById(detalle.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getDescripcion()).isEqualTo("Descripcion");
    }
}

