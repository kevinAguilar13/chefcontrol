package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorias_id")
    private Integer categoriasId;

    @Column(name = "categorias_nombre", nullable = false, length = 100)
    private String categoriasNombre;

    @Column(name = "categorias_descripcion")
    private String categoriasDescripcion;

    @Column(name = "categorias_activa")
    private Boolean categoriasActiva = true;
}
