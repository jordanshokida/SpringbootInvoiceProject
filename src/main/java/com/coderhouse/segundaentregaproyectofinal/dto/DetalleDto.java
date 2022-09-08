package com.coderhouse.segundaentregaproyectofinal.dto;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDto {

    private String nombreProducto;

    private String descripcionProducto;

    private int cantidad;

}
