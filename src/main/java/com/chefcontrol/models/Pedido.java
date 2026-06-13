package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedidos_id")
    private Integer pedidosId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_mesa_id")
    private Mesa mesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_tipo_pedido_id")
    private TipoPedido tipoPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_empleado_id")
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    @Column(name = "pedidos_estado", nullable = false)
    private EstadoPedido pedidosEstado = EstadoPedido.PENDIENTE;

    @Column(name = "pedidos_fecha_hora", nullable = false)
    private LocalDateTime pedidosFechaHora = LocalDateTime.now();

    @Column(name = "pedidos_total", precision = 10, scale = 2)
    private BigDecimal pedidosTotal = BigDecimal.ZERO;

    @Column(name = "pedidos_observaciones")
    private String pedidosObservaciones;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;

    public enum EstadoPedido {
        PENDIENTE, EN_PREPARACION, LISTO, ENTREGADO, CANCELADO
    }
}
