package com.chefcontrol.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chefcontrol.models.ConfiguracionRestaurante;
import com.chefcontrol.services.ConfiguracionRestauranteService;

@RestController
@RequestMapping("/api/configuracion-restaurante")
@CrossOrigin("*")
public class ConfiguracionRestauranteController {

    private final ConfiguracionRestauranteService configuracionService;

    public ConfiguracionRestauranteController(
            ConfiguracionRestauranteService configuracionService) {

        this.configuracionService = configuracionService;
    }

    @GetMapping
    public ConfiguracionRestaurante obtenerConfiguracion() {
        return configuracionService.obtenerConfiguracion();
    }
}