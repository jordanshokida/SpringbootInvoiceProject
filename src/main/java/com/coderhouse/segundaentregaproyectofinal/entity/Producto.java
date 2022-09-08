package com.coderhouse.segundaentregaproyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Table(name = "PRODUCTOS")
@Entity(name = "PRODUCTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "referenceList"})
public class Producto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    @Column(name = "PRODUCTO_ID")
    private Long id;

    @NotEmpty(message = "Nombre no debe estar vacío")
    @Size(min = 2, max = 40, message = "El nombre debe contener entre 2 y 15 caracteres")
    @Column(name = "NOMBRE")
    private String nombre;

    @NotEmpty(message = "Descripción no debe estar vacío")
    @Size(min = 1, max = 80, message = "La descripción debe contener entre 1 y 80 caracteres")
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Min(0)
    @Column(name = "CANTIDAD_EN_STOCK")
    private int cantidadEnStock;

    @Min(1)
    @Column(name = "PRECIO")
    private double precio;

    @ManyToOne
    @JoinColumn(name="FACTURA_ID")
    @JsonBackReference
    private Factura factura;


}
