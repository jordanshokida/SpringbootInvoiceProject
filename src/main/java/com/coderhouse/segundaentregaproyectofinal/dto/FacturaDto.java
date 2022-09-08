package com.coderhouse.segundaentregaproyectofinal.dto;

import com.coderhouse.segundaentregaproyectofinal.entity.Cliente;
import lombok.*;



import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDto {


    private Cliente cliente;

    private List<DetalleDto> detalleDtos;

    private Long idEmpresa;

}