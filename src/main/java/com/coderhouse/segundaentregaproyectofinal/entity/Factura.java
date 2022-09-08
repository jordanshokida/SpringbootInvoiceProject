package com.coderhouse.segundaentregaproyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zaxxer.hikari.util.ConcurrentBag;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity(name = "FACTURA")
@Table(name = "FACTURAS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Factura implements Serializable {
    private static final long SERIALVERSIONUID = 1L;

    @Id
    @Column(name = "FACTURA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "MONTO_TOTAL")
    private double montoTotal;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "factura", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Detalle> detalle;

    @JsonIgnore
    @OneToMany(mappedBy="factura", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Producto> productos;


    public Detalle agregarDetalle(Detalle detalle) {
        getDetalle().add(detalle);
        detalle.setFactura(this);
        return detalle;
    }


    public Detalle removerDetalle(Detalle detalle) {
        getDetalle().remove(detalle);
        detalle.setFactura(null);
        return detalle;
    }
}

