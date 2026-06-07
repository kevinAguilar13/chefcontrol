package com.chefcontrol.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipos_pedidos")
@Data
public class TipoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipos_pedidos_id")
    private Integer tiposPedidosId;

    @Column(name = "tipos_pedidos_nombre")
    private String tiposPedidosNombre;
}