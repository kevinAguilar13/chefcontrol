package com.chefcontrol.services;

import com.chefcontrol.models.Mesa;
import com.chefcontrol.repository.MesaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public List<Mesa> listarMesasActivas() {
        return mesaRepository.findByMesasActivaTrue();
    }

    public List<Mesa> listarPorEstado(Mesa.EstadoMesa estado) {
        return mesaRepository.findByMesasEstado(estado);
    }

    public Optional<Mesa> obtenerPorId(Integer id) {
        return mesaRepository.findById(id);
    }

    public Mesa crear(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> actualizar(Integer id, Mesa datos) {
        return mesaRepository.findById(id).map(m -> {
            m.setMesasNumero(datos.getMesasNumero());
            m.setMesasCapacidad(datos.getMesasCapacidad());
            m.setMesasEstado(datos.getMesasEstado());
            m.setMesasActiva(datos.getMesasActiva());
            return mesaRepository.save(m);
        });
    }

    public Optional<Mesa> cambiarEstado(Integer id, Mesa.EstadoMesa estado) {
        return mesaRepository.findById(id).map(m -> {
            m.setMesasEstado(estado);
            return mesaRepository.save(m);
        });
    }

    public boolean eliminar(Integer id) {
        if (mesaRepository.existsById(id)) {
            mesaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
