package com.chefcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chefcontrol.models.ConfiguracionRestaurante;

public interface ConfiguracionRestauranteRepository
        extends JpaRepository<ConfiguracionRestaurante, Integer> {
}