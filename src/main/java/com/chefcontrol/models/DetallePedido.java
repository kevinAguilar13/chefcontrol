package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pedidos")
@Data
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalles_pedidos_id")
    private Integer detallesPedidosId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detalles_pedidos_pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detalles_pedidos_producto_id", nullable = false)
    private Producto producto;

    @Column(name = "detalles_pedidos_cantidad", nullable = false)
    private Integer detallesPedidosCantidad;

    @Column(name = "detalles_pedidos_precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal detallesPedidosPrecioUnitario;

    @Column(name = "detalles_pedidos_subtotal", precision = 10, scale = 2)
    private BigDecimal detallesPedidosSubtotal;

    @Column(name = "detalles_pedidos_observaciones")
    private String detallesPedidosObservaciones;
}
