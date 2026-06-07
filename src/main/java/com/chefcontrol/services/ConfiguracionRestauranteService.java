package com.chefcontrol.services;

import org.springframework.stereotype.Service;

import com.chefcontrol.models.ConfiguracionRestaurante;
import com.chefcontrol.repository.ConfiguracionRestauranteRepository;

@Service
public class ConfiguracionRestauranteService {

    private final ConfiguracionRestauranteRepository configuracionRepository;

    public ConfiguracionRestauranteService(
            ConfiguracionRestauranteRepository configuracionRepository) {

        this.configuracionRepository = configuracionRepository;
    }

    public ConfiguracionRestaurante obtenerConfiguracion() {
        return configuracionRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }
}