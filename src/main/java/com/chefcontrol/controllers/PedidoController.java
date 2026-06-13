package com.chefcontrol.controllers;

import com.chefcontrol.dto.PedidoRequestDTO;
import com.chefcontrol.models.Pedido;
import com.chefcontrol.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin("*")
@Tag(name = "Pedidos", description = "Gestión de pedidos del restaurante")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los pedidos")
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar pedidos por estado (PENDIENTE, EN_PREPARACION, LISTO, ENTREGADO, CANCELADO)")
    public List<Pedido> listarPorEstado(@PathVariable Pedido.EstadoPedido estado) {
        return pedidoService.listarPorEstado(estado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por ID")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Integer id) {
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido con sus detalles")
    public ResponseEntity<Pedido> crear(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(dto));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar el estado de un pedido")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Integer id,
                                                 @RequestParam Pedido.EstadoPedido estado) {
        return pedidoService.cambiarEstado(id, estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar/eliminar un pedido")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return pedidoService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
