package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productos_id")
    private Integer productosId;

    @Column(name = "productos_nombre")
    private String productosNombre;

    @Column(name = "productos_descripcion")
    private String productosDescripcion;

    @Column(name = "productos_precio")
    private BigDecimal productosPrecio;

    @Column(name = "productos_tiempo_preparacion")
    private Integer productosTiempoPreparacion;

    @Column(name = "productos_disponible")
    private Boolean productosDisponible;
}