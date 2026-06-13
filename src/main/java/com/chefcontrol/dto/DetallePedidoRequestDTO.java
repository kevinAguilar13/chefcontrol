package com.chefcontrol.dto;

import lombok.Data;

@Data
public class DetallePedidoRequestDTO {

    private Integer productoId;
    private Integer cantidad;
    private String observaciones;
}
