package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientes_id")
    private Integer clientesId;

    @Column(name = "clientes_nombre", nullable = false, length = 150)
    private String clientesNombre;

    @Column(name = "clientes_telefono", length = 20)
    private String clientesTelefono;

    @Column(name = "clientes_email", length = 150)
    private String clientesEmail;

    @Column(name = "clientes_activo")
    private Boolean clientesActivo = true;
}
