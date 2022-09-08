package com.coderhouse.segundaentregaproyectofinal.service;

import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;
import com.coderhouse.segundaentregaproyectofinal.entity.Producto;
import com.coderhouse.segundaentregaproyectofinal.expcetion.DbException;
import com.coderhouse.segundaentregaproyectofinal.repository.DetalleRepository;
import com.coderhouse.segundaentregaproyectofinal.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DetalleServiceImpl implements DetalleService {
    @Autowired
    DetalleRepository detalleRepository;
    @Autowired
    ProductoRepository productoRepository;


    public Detalle crearDetalle(Detalle item) {return detalleRepository.save(item);
    }

    public void modificarCantidadDetallePorId(Long id, int cant) {
        Detalle itemModificado = obtenerDetallePorId(id);
        itemModificado.setCantidad(cant);
        itemModificado.setTotalParcial(itemModificado.getCantidad() * itemModificado.getTotalParcial());
        detalleRepository.save(itemModificado);

    }


    public void borrarDetalle(Long id) {
        Detalle item = obtenerDetallePorId(id);
        log.info("Se va a borrar el detalle {}", item.getNombreProducto());
        detalleRepository.deleteById(id);
    }

    public Detalle obtenerDetallePorId(Long id){

        Optional<Detalle> item = detalleRepository.findById(id);

        if (item.isPresent()) {

            return item.get();

        } else {

            throw new DbException("El detalle con el id " + id + " no existe");
        }


    }

    public List<Detalle> obtenerTodosLosDetalles() {
        return detalleRepository.findAll();
    }

}