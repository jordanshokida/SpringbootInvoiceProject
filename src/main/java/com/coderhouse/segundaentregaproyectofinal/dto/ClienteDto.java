package com.coderhouse.segundaentregaproyectofinal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private String nombre;

    private String apellido;

    private String direccion;

    private String dni;

}
