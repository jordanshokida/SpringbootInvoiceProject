package com.coderhouse.segundaentregaproyectofinal.service;


import com.coderhouse.segundaentregaproyectofinal.entity.Producto;

import java.util.List;

public interface ProductoService {

    Producto crearProducto(Producto producto);

    void modificarPrecioProducto(Long id, double precio);

    void borrarProducto(Long id);

    Producto obtenerProductoPorId(Long id);

    List<Producto> obtenerTodosLosProductos();

   void restarStockProductos(Long id, int cant);

    List<Producto> obtenerProductosPorNombre(String nombre);

    Producto obtenerProductoPorNombreYDescripcion(String nombre, String descripcion);

}
