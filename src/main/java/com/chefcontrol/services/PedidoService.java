package com.chefcontrol.services;

import com.chefcontrol.dto.DetallePedidoRequestDTO;
import com.chefcontrol.dto.PedidoRequestDTO;
import com.chefcontrol.models.*;
import com.chefcontrol.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final MesaRepository mesaRepository;
    private final ClienteRepository clienteRepository;
    private final TipoPedidoRepository tipoPedidoRepository;
    private final EmpleadoRepository empleadoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ProductoRepository productoRepository,
                         MesaRepository mesaRepository,
                         ClienteRepository clienteRepository,
                         TipoPedidoRepository tipoPedidoRepository,
                         EmpleadoRepository empleadoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.mesaRepository = mesaRepository;
        this.clienteRepository = clienteRepository;
        this.tipoPedidoRepository = tipoPedidoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPorEstado(Pedido.EstadoPedido estado) {
        return pedidoRepository.findByPedidosEstado(estado);
    }

    public Optional<Pedido> obtenerPorId(Integer id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido crear(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setPedidosFechaHora(LocalDateTime.now());
        pedido.setPedidosEstado(Pedido.EstadoPedido.PENDIENTE);
        pedido.setPedidosObservaciones(dto.getObservaciones());

        if (dto.getMesaId() != null) {
            mesaRepository.findById(dto.getMesaId()).ifPresent(pedido::setMesa);
        }
        if (dto.getClienteId() != null) {
            clienteRepository.findById(dto.getClienteId()).ifPresent(pedido::setCliente);
        }
        if (dto.getTipoPedidoId() != null) {
            tipoPedidoRepository.findById(dto.getTipoPedidoId()).ifPresent(pedido::setTipoPedido);
        }
        if (dto.getEmpleadoId() != null) {
            empleadoRepository.findById(dto.getEmpleadoId()).ifPresent(pedido::setEmpleado);
        }

        // Guardar el pedido primero
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Procesar detalles
        List<DetallePedido> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        if (dto.getDetalles() != null) {
            for (DetallePedidoRequestDTO detalleDto : dto.getDetalles()) {
                Optional<Producto> productoOpt = productoRepository.findById(detalleDto.getProductoId());
                if (productoOpt.isPresent()) {
                    Producto producto = productoOpt.get();
                    DetallePedido detalle = new DetallePedido();
                    detalle.setPedido(pedidoGuardado);
                    detalle.setProducto(producto);
                    detalle.setDetallesPedidosCantidad(detalleDto.getCantidad());
                    detalle.setDetallesPedidosPrecioUnitario(producto.getProductosPrecio());
                    BigDecimal subtotal = producto.getProductosPrecio()
                            .multiply(BigDecimal.valueOf(detalleDto.getCantidad()));
                    detalle.setDetallesPedidosSubtotal(subtotal);
                    detalle.setDetallesPedidosObservaciones(detalleDto.getObservaciones());
                    detalles.add(detalle);
                    total = total.add(subtotal);
                }
            }
        }

        pedidoGuardado.setDetalles(detalles);
        pedidoGuardado.setPedidosTotal(total);
        return pedidoRepository.save(pedidoGuardado);
    }

    public Optional<Pedido> cambiarEstado(Integer id, Pedido.EstadoPedido estado) {
        return pedidoRepository.findById(id).map(p -> {
            p.setPedidosEstado(estado);
            return pedidoRepository.save(p);
        });
    }

    public boolean eliminar(Integer id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
