package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "empleados")
@Data
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleados_id")
    private Integer empleadosId;

    @Column(name = "empleados_nombre", nullable = false, length = 150)
    private String empleadosNombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "empleados_rol", nullable = false)
    private RolEmpleado empleadosRol;

    @Column(name = "empleados_telefono", length = 20)
    private String empleadosTelefono;

    @Column(name = "empleados_email", length = 150)
    private String empleadosEmail;

    @Column(name = "empleados_activo")
    private Boolean empleadosActivo = true;

    public enum RolEmpleado {
        MESERO, COCINERO, CAJERO, ADMINISTRADOR, BARTENDER
    }
}
