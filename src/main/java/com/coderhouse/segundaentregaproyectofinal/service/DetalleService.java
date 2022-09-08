package com.coderhouse.segundaentregaproyectofinal.service;



import com.coderhouse.segundaentregaproyectofinal.entity.Detalle;

import java.util.List;

public interface DetalleService {

    Detalle crearDetalle(Detalle item);

    void modificarCantidadDetallePorId(Long id, int cant);

   void borrarDetalle(Long id);

    Detalle obtenerDetallePorId(Long id);

    List<Detalle> obtenerTodosLosDetalles();

}