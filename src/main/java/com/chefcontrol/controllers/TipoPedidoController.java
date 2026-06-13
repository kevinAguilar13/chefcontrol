package com.chefcontrol.controllers;

import com.chefcontrol.models.TipoPedido;
import com.chefcontrol.services.TipoPedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-pedido")
@CrossOrigin("*")
@Tag(name = "Tipos de Pedido", description = "Gestión de tipos de pedido (Mesa, Para llevar, Delivery, etc.)")
public class TipoPedidoController {

    private final TipoPedidoService tipoPedidoService;

    public TipoPedidoController(TipoPedidoService tipoPedidoService) {
        this.tipoPedidoService = tipoPedidoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los tipos de pedido")
    public List<TipoPedido> listarTiposPedido() {
        return tipoPedidoService.listarTiposPedido();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo de pedido por ID")
    public ResponseEntity<TipoPedido> obtenerPorId(@PathVariable Integer id) {
        return tipoPedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo tipo de pedido")
    public ResponseEntity<TipoPedido> crear(@RequestBody TipoPedido tipoPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoPedidoService.crear(tipoPedido));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un tipo de pedido")
    public ResponseEntity<TipoPedido> actualizar(@PathVariable Integer id, @RequestBody TipoPedido tipoPedido) {
        return tipoPedidoService.actualizar(id, tipoPedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un tipo de pedido")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return tipoPedidoService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}