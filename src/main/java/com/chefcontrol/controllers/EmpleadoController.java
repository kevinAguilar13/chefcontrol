package com.chefcontrol.controllers;

import com.chefcontrol.models.Empleado;
import com.chefcontrol.services.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin("*")
@Tag(name = "Empleados", description = "Gestión del personal del restaurante")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los empleados")
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/activos")
    @Operation(summary = "Listar empleados activos")
    public List<Empleado> listarActivos() {
        return empleadoService.listarEmpleadosActivos();
    }

    @GetMapping("/rol/{rol}")
    @Operation(summary = "Listar empleados por rol (MESERO, COCINERO, CAJERO, ADMINISTRADOR, BARTENDER)")
    public List<Empleado> listarPorRol(@PathVariable Empleado.RolEmpleado rol) {
        return empleadoService.listarPorRol(rol);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID")
    public ResponseEntity<Empleado> obtenerPorId(@PathVariable Integer id) {
        return empleadoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo empleado")
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.crear(empleado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un empleado")
    public ResponseEntity<Empleado> actualizar(@PathVariable Integer id, @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return empleadoService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
