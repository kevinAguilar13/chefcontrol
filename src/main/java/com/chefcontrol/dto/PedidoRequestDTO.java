package com.chefcontrol.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoRequestDTO {

    private Integer mesaId;
    private Integer clienteId;
    private Integer tipoPedidoId;
    private Integer empleadoId;
    private String observaciones;
    private List<DetallePedidoRequestDTO> detalles;
}
