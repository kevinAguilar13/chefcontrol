package com.chefcontrol.services;

import com.chefcontrol.models.TipoPedido;
import com.chefcontrol.repository.TipoPedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoPedidoService {

    private final TipoPedidoRepository tipoPedidoRepository;

    public TipoPedidoService(TipoPedidoRepository tipoPedidoRepository) {
        this.tipoPedidoRepository = tipoPedidoRepository;
    }

    public List<TipoPedido> listarTiposPedido() {
        return tipoPedidoRepository.findAll();
    }

    public Optional<TipoPedido> obtenerPorId(Integer id) {
        return tipoPedidoRepository.findById(id);
    }

    public TipoPedido crear(TipoPedido tipoPedido) {
        return tipoPedidoRepository.save(tipoPedido);
    }

    public Optional<TipoPedido> actualizar(Integer id, TipoPedido datos) {
        return tipoPedidoRepository.findById(id).map(t -> {
            t.setTiposPedidosNombre(datos.getTiposPedidosNombre());
            return tipoPedidoRepository.save(t);
        });
    }

    public boolean eliminar(Integer id) {
        if (tipoPedidoRepository.existsById(id)) {
            tipoPedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}