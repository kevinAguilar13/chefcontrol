package com.chefcontrol.services;

import com.chefcontrol.models.Cliente;
import com.chefcontrol.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public List<Cliente> listarClientesActivos() {
        return clienteRepository.findByClientesActivoTrue();
    }

    public Optional<Cliente> obtenerPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> actualizar(Integer id, Cliente datos) {
        return clienteRepository.findById(id).map(c -> {
            c.setClientesNombre(datos.getClientesNombre());
            c.setClientesTelefono(datos.getClientesTelefono());
            c.setClientesEmail(datos.getClientesEmail());
            c.setClientesActivo(datos.getClientesActivo());
            return clienteRepository.save(c);
        });
    }

    public boolean eliminar(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
