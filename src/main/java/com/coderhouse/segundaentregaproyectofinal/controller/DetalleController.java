package com.coderhouse.segundaentregaproyectofinal.controller;

import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;
import com.coderhouse.segundaentregaproyectofinal.service.DetalleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
public class DetalleController {

    @Autowired
    DetalleService detalleService;

    @PostMapping("/setItem")
    public ResponseEntity<Detalle> setItem(@RequestBody Detalle item) {
        Detalle nuevoItem = detalleService.crearDetalle(item);
        return ResponseEntity.ok().body(nuevoItem);
    }

    @PutMapping("/updateCantidadItem")
    public void updateCantidadItem(@Param("id") Long id, @Param("cant") int cant) {
        detalleService.modificarCantidadDetallePorId(id, cant);

    }

    @DeleteMapping("/deleteItem/{id}")
    public void deleteItem(@PathVariable(value = "id") Long itemId) {
        detalleService.borrarDetalle(itemId);
    }

    @GetMapping("/getItemById/{id}")
    public ResponseEntity<Detalle> getItem(@PathVariable(value = "id") Long itemId) {
        Detalle item = detalleService.obtenerDetallePorId(itemId);
        return ResponseEntity.ok().body(item);
    }

    @GetMapping("/getItemsList")
    public ResponseEntity<List<Detalle>> getAllItems() {
        List<Detalle> itemList = detalleService.obtenerTodosLosDetalles();
        return ResponseEntity.ok().body(itemList);
    }
}
