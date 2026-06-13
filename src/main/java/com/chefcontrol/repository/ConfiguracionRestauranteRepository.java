package com.chefcontrol.repository;

import com.chefcontrol.models.ConfiguracionRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionRestauranteRepository extends JpaRepository<ConfiguracionRestaurante, Integer> {
}