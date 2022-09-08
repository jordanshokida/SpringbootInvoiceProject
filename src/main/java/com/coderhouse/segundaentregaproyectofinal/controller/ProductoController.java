package com.coderhouse.segundaentregaproyectofinal.controller;

import com.coderhouse.segundaentregaproyectofinal.entity.Producto;
import com.coderhouse.segundaentregaproyectofinal.service.ProductoService;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Data
@RestController
public class ProductoController {
    @Autowired
    ProductoService productoService;


    @GetMapping("/getProductoById/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable(value = "id")  Long productoId) {
        Producto producto = productoService.obtenerProductoPorId(productoId);
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping("/getProductosListByNombre")
    public ResponseEntity<List<Producto>> getProductosListByNombre(@Param("nombre") String nombre) {
        List<Producto> producto = productoService.obtenerProductosPorNombre(nombre);
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping("/getProductoByNombreYDescripcion")
    public ResponseEntity<Producto> getProductoByNombreYDescricpion(@Param("nombre") String nombre, @Param("descripcion") String descripcion) {
        Producto producto = productoService.obtenerProductoPorNombreYDescripcion(nombre, descripcion);
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productosList = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok().body(productosList);
    }

    @PostMapping("/setproducto")
    public ResponseEntity<Producto> setProducto(@RequestBody  Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok().body(nuevoProducto);
    }


    @DeleteMapping("/deleteProducto/{id}")
    public void deleteProducto(@PathVariable(value = "id")  Long productoId) {
        productoService.borrarProducto(productoId);
    }

    @PutMapping("/updatePrecioProducto")
    public void updatePrecioProducto(@Param("id") Long id, @Param("precio") double precio) {
        productoService.modificarPrecioProducto(id, precio);

    }

    @PutMapping("/updateStockProducto")
    public void restarStockProducto(@Param("id") Long id, @Param("cant") int cant) {
        productoService.restarStockProductos(id, cant);


    }
}