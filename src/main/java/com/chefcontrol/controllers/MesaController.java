package com.chefcontrol.controllers;

import com.chefcontrol.models.Mesa;
import com.chefcontrol.services.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin("*")
@Tag(name = "Mesas", description = "Gestión de mesas del restaurante")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las mesas")
    public List<Mesa> listarMesas() {
        return mesaService.listarMesas();
    }

    @GetMapping("/activas")
    @Operation(summary = "Listar mesas activas")
    public List<Mesa> listarActivas() {
        return mesaService.listarMesasActivas();
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar mesas por estado (DISPONIBLE, OCUPADA, RESERVADA, FUERA_DE_SERVICIO)")
    public List<Mesa> listarPorEstado(@PathVariable Mesa.EstadoMesa estado) {
        return mesaService.listarPorEstado(estado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener mesa por ID")
    public ResponseEntity<Mesa> obtenerPorId(@PathVariable Integer id) {
        return mesaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva mesa")
    public ResponseEntity<Mesa> crear(@RequestBody Mesa mesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.crear(mesa));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una mesa")
    public ResponseEntity<Mesa> actualizar(@PathVariable Integer id, @RequestBody Mesa mesa) {
        return mesaService.actualizar(id, mesa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado de una mesa")
    public ResponseEntity<Mesa> cambiarEstado(@PathVariable Integer id,
                                               @RequestParam Mesa.EstadoMesa estado) {
        return mesaService.cambiarEstado(id, estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una mesa")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return mesaService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
