package com.chefcontrol.services;

import com.chefcontrol.models.ConfiguracionRestaurante;
import com.chefcontrol.repository.ConfiguracionRestauranteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracionRestauranteService {

    private final ConfiguracionRestauranteRepository configuracionRepository;

    public ConfiguracionRestauranteService(ConfiguracionRestauranteRepository configuracionRepository) {
        this.configuracionRepository = configuracionRepository;
    }

    public List<ConfiguracionRestaurante> listarConfiguraciones() {
        return configuracionRepository.findAll();
    }

    public ConfiguracionRestaurante obtenerConfiguracion() {
        return configuracionRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Optional<ConfiguracionRestaurante> obtenerPorId(Integer id) {
        return configuracionRepository.findById(id);
    }

    public ConfiguracionRestaurante crear(ConfiguracionRestaurante config) {
        return configuracionRepository.save(config);
    }

    public Optional<ConfiguracionRestaurante> actualizar(Integer id, ConfiguracionRestaurante datos) {
        return configuracionRepository.findById(id).map(c -> {
            c.setConfiguracionesRestaurantesNombre(datos.getConfiguracionesRestaurantesNombre());
            c.setConfiguracionesRestaurantesTelefono(datos.getConfiguracionesRestaurantesTelefono());
            c.setConfiguracionesRestaurantesDireccion(datos.getConfiguracionesRestaurantesDireccion());
            c.setConfiguracionesRestaurantesPedidosHabilitados(datos.getConfiguracionesRestaurantesPedidosHabilitados());
            c.setConfiguracionesRestaurantesHoraInicio(datos.getConfiguracionesRestaurantesHoraInicio());
            c.setConfiguracionesRestaurantesHoraFin(datos.getConfiguracionesRestaurantesHoraFin());
            c.setConfiguracionesRestaurantesMaxMesas(datos.getConfiguracionesRestaurantesMaxMesas());
            return configuracionRepository.save(c);
        });
    }

    public boolean eliminar(Integer id) {
        if (configuracionRepository.existsById(id)) {
            configuracionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}