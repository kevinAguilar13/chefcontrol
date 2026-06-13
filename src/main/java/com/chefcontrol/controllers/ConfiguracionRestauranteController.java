package com.chefcontrol.controllers;

import com.chefcontrol.models.ConfiguracionRestaurante;
import com.chefcontrol.services.ConfiguracionRestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/configuracion-restaurante")
@CrossOrigin("*")
@Tag(name = "Configuración del Restaurante", description = "Configuración general del restaurante")
public class ConfiguracionRestauranteController {

    private final ConfiguracionRestauranteService configuracionService;

    public ConfiguracionRestauranteController(ConfiguracionRestauranteService configuracionService) {
        this.configuracionService = configuracionService;
    }

    @GetMapping
    @Operation(summary = "Obtener la configuración activa del restaurante")
    public ResponseEntity<ConfiguracionRestaurante> obtenerConfiguracion() {
        ConfiguracionRestaurante config = configuracionService.obtenerConfiguracion();
        return config != null ? ResponseEntity.ok(config) : ResponseEntity.notFound().build();
    }

    @GetMapping("/todas")
    @Operation(summary = "Listar todas las configuraciones")
    public List<ConfiguracionRestaurante> listarConfiguraciones() {
        return configuracionService.listarConfiguraciones();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener configuración por ID")
    public ResponseEntity<ConfiguracionRestaurante> obtenerPorId(@PathVariable Integer id) {
        return configuracionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear configuración del restaurante")
    public ResponseEntity<ConfiguracionRestaurante> crear(@RequestBody ConfiguracionRestaurante config) {
        return ResponseEntity.status(HttpStatus.CREATED).body(configuracionService.crear(config));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar configuración del restaurante")
    public ResponseEntity<ConfiguracionRestaurante> actualizar(@PathVariable Integer id,
                                                               @RequestBody ConfiguracionRestaurante config) {
        return configuracionService.actualizar(id, config)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar configuración")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return configuracionService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}