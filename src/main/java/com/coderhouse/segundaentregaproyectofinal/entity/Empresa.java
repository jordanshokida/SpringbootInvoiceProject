package com.coderhouse.segundaentregaproyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Table(name = "EMPRESA")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    @Column(name = "EMPRESA_ID")
    private Long id;

    @NotEmpty(message = "Nombre no debe estar vacío")
    @Size(min = 1, max = 35, message = "Nombre no puede estar vacío, y debe contener entre 1 y 35 caracteres")
    @Column(name = "NOMBRE")
    private String nombre;

    @NotEmpty(message = "Rubro no debe estar vacío")
    @Size(min = 1, max = 60, message = "Rubro no puede estar vacío, y debe contener entre 1 y 60 caracteres")
    @Column(name = "RUBRO")
    private String rubro;

    @NotEmpty(message = "Cuit no debe estar vacío")
    @Column(name = "CUIT")
    private String cuit;

    @JsonIgnore
    @OneToMany(mappedBy="empresa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Factura> factura;

}
