package com.chefcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chefcontrol.models.TipoPedido;

public interface TipoPedidoRepository extends JpaRepository<TipoPedido, Integer> {
}