package com.coderhouse.segundaentregaproyectofinal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {


    private String nombreProducto;

    private String descripcionProducto;

    private double precioUnitario;

}


