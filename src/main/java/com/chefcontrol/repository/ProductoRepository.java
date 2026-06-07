package com.chefcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chefcontrol.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}