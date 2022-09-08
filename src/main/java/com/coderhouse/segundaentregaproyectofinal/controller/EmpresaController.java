package com.coderhouse.segundaentregaproyectofinal.controller;

import com.coderhouse.segundaentregaproyectofinal.entity.Empresa;
import com.coderhouse.segundaentregaproyectofinal.service.EmpresaService;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Data
@RestController
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/getEmpresa/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable(value = "id")  Long empresaId) {
        Empresa empresa = empresaService.obtenerEmpresaPorId(empresaId);
        return ResponseEntity.ok().body(empresa);
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresasList = empresaService.obtenerTodasLasEmpresas();
        return ResponseEntity.ok().body(empresasList);
    }

    @PostMapping("/setEmpresa")
    public ResponseEntity<Empresa> setEmpresa(@RequestBody  Empresa empresa) {
        Empresa nuevaEmpresa = empresaService.crearEmpresa(empresa);
        return ResponseEntity.ok().body(nuevaEmpresa);
    }


    @DeleteMapping("/deleteEmpresa/{id}")
    public void deleteEmpresa(@PathVariable(value = "id")  Long empresaId) {
        empresaService.borrarEmpresaPorId(empresaId);
    }

}


