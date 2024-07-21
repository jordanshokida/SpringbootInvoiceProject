package com.coderhouse.segundaentregaproyectofinal.RepositoryTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Empresa;
import com.coderhouse.segundaentregaproyectofinal.repository.EmpresaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    void testFindByNombre() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNombre("Empresa1");
        empresa.setCuit("20372737361");
        empresa.setRubro("Rubro");
        empresaRepository.save(empresa);

        Optional<Empresa> found = empresaRepository.findByNombre("Empresa1");
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Empresa1");
    }
}

