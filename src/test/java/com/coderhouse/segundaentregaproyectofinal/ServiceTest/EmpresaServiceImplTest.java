package com.coderhouse.segundaentregaproyectofinal.ServiceTest;

import com.coderhouse.segundaentregaproyectofinal.entity.Empresa;
import com.coderhouse.segundaentregaproyectofinal.repository.EmpresaRepository;
import com.coderhouse.segundaentregaproyectofinal.exception.ResourceAlreadyExistsException;
import com.coderhouse.segundaentregaproyectofinal.service.EmpresaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceImplTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaServiceImpl empresaService;

    @Test
    void testCrearEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNombre("Empresa1");
        empresa.setCuit("20372737361");
        empresa.setRubro("Rubro");

        when(empresaRepository.findByNombre("Empresa1")).thenReturn(Optional.empty());
        when(empresaRepository.save(empresa)).thenReturn(empresa);

        Empresa result = empresaService.crearEmpresa(empresa);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Empresa1", result.getNombre());
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    void testCrearEmpresa_AlreadyExists() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNombre("Empresa1");
        empresa.setCuit("20372737361");
        empresa.setRubro("Rubro");

        when(empresaRepository.findByNombre("Empresa1")).thenReturn(Optional.of(empresa));

        assertThrows(ResourceAlreadyExistsException.class, () -> empresaService.crearEmpresa(empresa));
        verify(empresaRepository, never()).save(any(Empresa.class));
    }

    /*@Test
    void testObtenerEmpresaPorId() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNombre("Empresa1");
        empresa.setCuit("20372737361");
        empresa.setRubro("Rubro");

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        Empresa result = empresaService.obtenerEmpresaPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Empresa1", result.getNombre());
        verify(empresaRepository, times(1)).findById(1L);
    }*/
}

