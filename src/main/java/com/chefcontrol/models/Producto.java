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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productos_categoria_id")
    private Categoria categoria;

    @Column(name = "productos_nombre", nullable = false, length = 150)
    private String productosNombre;

    @Column(name = "productos_descripcion")
    private String productosDescripcion;

    @Column(name = "productos_precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal productosPrecio;

    @Column(name = "productos_tiempo_preparacion")
    private Integer productosTiempoPreparacion;

    @Column(name = "productos_disponible")
    private Boolean productosDisponible = true;

    @Column(name = "productos_imagen_url")
    private String productosImagenUrl;
}