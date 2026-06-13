package com.chefcontrol.controllers;

import com.chefcontrol.models.Producto;
import com.chefcontrol.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
@Tag(name = "Productos", description = "Gestión del menú de productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Listar productos disponibles")
    public List<Producto> listarDisponibles() {
        return productoService.listarDisponibles();
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Listar productos por categoría")
    public List<Producto> listarPorCategoria(@PathVariable Integer categoriaId) {
        return productoService.listarPorCategoria(categoriaId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto")
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(producto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
        return productoService.actualizar(id, producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return productoService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}