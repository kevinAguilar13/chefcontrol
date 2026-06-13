package com.chefcontrol.controllers;

import com.chefcontrol.models.Categoria;
import com.chefcontrol.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
@Tag(name = "Categorías", description = "Gestión de categorías de productos")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las categorías")
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva categoría")
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crear(categoria));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría")
    public ResponseEntity<Categoria> actualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
        return categoriaService.actualizar(id, categoria)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return categoriaService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
