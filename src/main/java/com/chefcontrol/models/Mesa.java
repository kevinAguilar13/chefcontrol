package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mesas")
@Data
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mesas_id")
    private Integer mesasId;

    @Column(name = "mesas_numero", nullable = false)
    private Integer mesasNumero;

    @Column(name = "mesas_capacidad", nullable = false)
    private Integer mesasCapacidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "mesas_estado", nullable = false)
    private EstadoMesa mesasEstado = EstadoMesa.DISPONIBLE;

    @Column(name = "mesas_activa")
    private Boolean mesasActiva = true;

    public enum EstadoMesa {
        DISPONIBLE, OCUPADA, RESERVADA, FUERA_DE_SERVICIO
    }
}
