package com.chefcontrol.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "configuraciones_restaurantes")
@Data
public class ConfiguracionRestaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuraciones_restaurantes_id")
    private Integer configuracionesRestaurantesId;

    @Column(name = "configuraciones_restaurantes_pedidos_habilitados")
    private Boolean configuracionesRestaurantesPedidosHabilitados;

    @Column(name = "configuraciones_restaurantes_hora_inicio")
    private LocalDateTime configuracionesRestaurantesHoraInicio;

    @Column(name = "configuraciones_restaurantes_hora_fin")
    private LocalDateTime configuracionesRestaurantesHoraFin;
}