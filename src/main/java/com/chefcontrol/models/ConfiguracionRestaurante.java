package com.chefcontrol.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Table(name = "configuraciones_restaurantes")
@Data
public class ConfiguracionRestaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuraciones_restaurantes_id")
    private Integer configuracionesRestaurantesId;

    @Column(name = "configuraciones_restaurantes_nombre", length = 200)
    private String configuracionesRestaurantesNombre;

    @Column(name = "configuraciones_restaurantes_telefono", length = 30)
    private String configuracionesRestaurantesTelefono;

    @Column(name = "configuraciones_restaurantes_direccion")
    private String configuracionesRestaurantesDireccion;

    @Column(name = "configuraciones_restaurantes_pedidos_habilitados")
    private Boolean configuracionesRestaurantesPedidosHabilitados = true;

    @Column(name = "configuraciones_restaurantes_hora_inicio")
    private LocalTime configuracionesRestaurantesHoraInicio;

    @Column(name = "configuraciones_restaurantes_hora_fin")
    private LocalTime configuracionesRestaurantesHoraFin;

    @Column(name = "configuraciones_restaurantes_max_mesas")
    private Integer configuracionesRestaurantesMaxMesas;
}