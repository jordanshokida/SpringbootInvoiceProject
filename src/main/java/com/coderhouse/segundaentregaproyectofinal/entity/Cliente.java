package com.coderhouse.segundaentregaproyectofinal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Table(name = "CLIENTES")
@Entity(name = "CLIENTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClIENTE_ID")
    private Long id;

    @NotEmpty(message = "Nombre no debe estar vacío")
    @Size(min = 2, max = 15, message = "El nombre debe contener entre 2 y 15 caracteres")
    @Column(name = "NOMBRE")
    private String nombre;

    @NotEmpty(message = "Apellido no debe estar vacío")
    @Size(min = 2, max = 15, message = "El apellido debe contener entre 2 y 15 caracteres")
    @Column(name = "APELLIDO")
    private String apellido;

    @NotEmpty(message = "Dirección no puede estar vacío")
    @Size(min = 4, max = 40, message = "La dirección debe contener entre 4 y 40 caracteres")
    @Column(name = "DIRECCION")
    private String direccion;

    @NotEmpty(message = "Dni no debe estar vacío")
    @Size(min = 7, message = "El dni no debe contener menos de 7 números")
    @Column(name = "DNI")
    private String dni;

    @NotEmpty(message = "Teléfono no puede estar vacío")
    @Size(min = 8, message = "El teléfono no debe contener menos de 8 números")
    @Column(name = "TELEFONO")
    private String telefono;

    @Email(message = "Ingrese correctamente el email")
    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy="cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Factura> facturas;

}
