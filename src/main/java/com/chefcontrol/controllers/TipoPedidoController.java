package com.chefcontrol.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chefcontrol.models.TipoPedido;
import com.chefcontrol.services.TipoPedidoService;

@RestController
@RequestMapping("/api/tipos-pedidos")
@CrossOrigin("*")
public class TipoPedidoController {

    private final TipoPedidoService tipoPedidoService;

    public TipoPedidoController(TipoPedidoService tipoPedidoService) {
        this.tipoPedidoService = tipoPedidoService;
    }

    @GetMapping
    public List<TipoPedido> listarTiposPedidos() {
        return tipoPedidoService.listarTiposPedidos();
    }
}