package com.coderhouse.segundaentregaproyectofinal.ControllerTest;

import com.coderhouse.segundaentregaproyectofinal.controller.EmpresaController;
import com.coderhouse.segundaentregaproyectofinal.entity.Empresa;
import com.coderhouse.segundaentregaproyectofinal.service.EmpresaService;
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
public class EmpresaControllerTest {

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    @Test
    void testGetEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setId(1L);

        when(empresaService.obtenerEmpresaPorId(anyLong())).thenReturn(empresa);

        ResponseEntity<Empresa> response = empresaController.getEmpresa(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresa, response.getBody());
    }

    @Test
    void testGetAllEmpresas() {
        Empresa empresa1 = new Empresa();
        empresa1.setId(1L);

        Empresa empresa2 = new Empresa();
        empresa2.setId(2L);

        List<Empresa> empresas = Arrays.asList(empresa1, empresa2);

        when(empresaService.obtenerTodasLasEmpresas()).thenReturn(empresas);

        ResponseEntity<List<Empresa>> response = empresaController.getAllEmpresas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresas, response.getBody());
    }

    @Test
    void testSetEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setNombre("Empresa1");

        when(empresaService.crearEmpresa(empresa)).thenReturn(empresa);

        ResponseEntity<Empresa> response = empresaController.setEmpresa(empresa);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empresa, response.getBody());
    }
}

