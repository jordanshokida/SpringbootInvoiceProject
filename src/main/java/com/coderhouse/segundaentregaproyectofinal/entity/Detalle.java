package com.coderhouse.segundaentregaproyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "DETALLE")
@Table(name = "DETALLES")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Detalle implements Serializable {

    private static final long SERIALVERSIONUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "DETALLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;

    @NotEmpty(message = "Descripción no debe estar vacío")
    @Size(min = 1, max = 80, message = "La descripción debe contener entre 1 y 255 caracteres")
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Min(1)
    @Column(name = "CANTIDAD")
    private int cantidad;

    @Min(0)
    @Column(name = "TOTAL_PARCIAL")
    private double totalParcial;

    @ManyToOne
    @JoinColumn(name = "FACTURA_ID")
    @JsonBackReference
    private Factura factura;

}

