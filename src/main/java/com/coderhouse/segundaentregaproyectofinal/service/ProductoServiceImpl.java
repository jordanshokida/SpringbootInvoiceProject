package com.coderhouse.segundaentregaproyectofinal.service;

import com.coderhouse.segundaentregaproyectofinal.entity.Producto;
import com.coderhouse.segundaentregaproyectofinal.expcetion.DbExpcetion;
import com.coderhouse.segundaentregaproyectofinal.expcetion.ResourceNotFoundException;
import com.coderhouse.segundaentregaproyectofinal.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void modificarPrecioProducto(Long id, double precio) {

        Producto productoModificado = obtenerProductoPorId(id);

        if(precio > 0) {

            productoModificado.setPrecio(precio);
            log.info("Se modificó el precio del producto: "+productoModificado.getNombre() + " a $" + precio);
            productoRepository.save(productoModificado);

        } else {

            throw new DbExpcetion("No se puede ingresar un precio negativo");

        }


    }

    public void borrarProducto(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id brindado no puede ser 0 ni negativo");
        }
        try{
            productoRepository.deleteById(id);
        }catch(RuntimeException exception){
            throw new DbExpcetion("No existe el producto con el id " + id);
        }
        Optional<Producto> producto = productoRepository.findById(id);
        if(!producto.isPresent()){
            log.info("El producto fue eliminado exitosamente, su id es" +id);

        }else{
            log.info("No se pudo eliminar el producto con el di: " +id);
        }
    }


    public Producto obtenerProductoPorId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id pedido no puede ser 0 ni negativo");
        }
        log.info("El id es correcto, se va a obtener el producto");
        this.productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con el ID " + id + " no existe"));
        return this.productoRepository.getById(id);
    }


    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }



    public void restarStockProductos(Long id, int cant)  {

        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {

            Producto productoModificado = producto.get();

            if (cant > 0) {

                if (productoModificado.getCantidadEnStock() >= cant) {

                    productoModificado.setCantidadEnStock(productoModificado.getCantidadEnStock() - cant);
                    productoRepository.save(productoModificado);

                } else {

                    throw new DbExpcetion("No hay suficiente stock para el producto: "+productoModificado.getNombre()+". La cantidad en Stock disponible es: "
                            + productoModificado.getCantidadEnStock());

                }

            } else {

                throw new DbExpcetion("No se puede ingresar una cantidad negativa");
            }

        } else {

            throw new DbExpcetion("El producto no existe");

        }
    }

    public List <Producto> obtenerProductosPorNombre(String nombre){

        return productoRepository.findAllByNombre(nombre);

    }

    public Producto obtenerProductoPorNombreYDescripcion(String nombre, String descripcion){

        Producto miProducto = null;
        List<Producto> misProductos = obtenerProductosPorNombre(nombre);

        for(Producto producto: misProductos) {

            if(producto.getDescripcion().equalsIgnoreCase(descripcion)) {
                miProducto = producto;
            }
        }

        if(miProducto == null) {

            throw new DbExpcetion("No existe ese producto en la bd");
        }

        return miProducto;

    }

}
