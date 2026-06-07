package com.chefcontrol.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chefcontrol.models.TipoPedido;
import com.chefcontrol.repository.TipoPedidoRepository;

@Service
public class TipoPedidoService {

    private final TipoPedidoRepository tipoPedidoRepository;

    public TipoPedidoService(TipoPedidoRepository tipoPedidoRepository) {
        this.tipoPedidoRepository = tipoPedidoRepository;
    }

    public List<TipoPedido> listarTiposPedidos() {
        return tipoPedidoRepository.findAll();
    }
}