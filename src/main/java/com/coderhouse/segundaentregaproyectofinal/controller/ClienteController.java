package com.coderhouse.segundaentregaproyectofinal.controller;

import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import com.coderhouse.segundaentregaproyectofinal.entity.Factura;
import com.coderhouse.segundaentregaproyectofinal.service.ClienteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/getCliente/id/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable(value = "id") Long clienteId) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clienteList = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok().body(clienteList);
    }

    @GetMapping("/getCliente/nombre")
    public Cliente getClienteByNombre(@RequestParam(value = "nombre") String nombre) {
        return clienteService.obtenerClientePorNombre(nombre);
    }


    @PostMapping("/setCliente")
    public ResponseEntity<Cliente> setCliente(@RequestBody @Valid Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok().body(nuevoCliente);
    }

    @PutMapping("/updateClienteTelefono")
    public void updateClienteTelefono(@Param("id") Long id, @Param("tel") String tel) {
        clienteService.modificarTelefonoCliente(id, tel);
    }

    @DeleteMapping("/deleteCliente/{id}")
    public void deleteCliente(@PathVariable(value = "id") @Valid Long clienteId) {
        clienteService.borrarCliente(clienteId);
    }

    @GetMapping("/getFacturasPorCliente/{id}")
    public ResponseEntity<List<Factura>> getAllFacturasByCliente(@PathVariable(value = "id") Long clienteId) {
        List<Factura> facturasCliente = clienteService.obtenerFacturasPorIdCliente(clienteId);
        return ResponseEntity.ok().body(facturasCliente);

    }

}

