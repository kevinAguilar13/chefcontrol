package com.chefcontrol.repository;

import com.chefcontrol.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByPedidosEstado(Pedido.EstadoPedido estado);
    List<Pedido> findByMesa_MesasId(Integer mesaId);
}
