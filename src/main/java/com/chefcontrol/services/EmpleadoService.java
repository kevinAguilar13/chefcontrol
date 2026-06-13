package com.chefcontrol.services;

import com.chefcontrol.models.Empleado;
import com.chefcontrol.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    public List<Empleado> listarEmpleadosActivos() {
        return empleadoRepository.findByEmpleadosActivoTrue();
    }

    public List<Empleado> listarPorRol(Empleado.RolEmpleado rol) {
        return empleadoRepository.findByEmpleadosRol(rol);
    }

    public Optional<Empleado> obtenerPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    public Empleado crear(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> actualizar(Integer id, Empleado datos) {
        return empleadoRepository.findById(id).map(e -> {
            e.setEmpleadosNombre(datos.getEmpleadosNombre());
            e.setEmpleadosRol(datos.getEmpleadosRol());
            e.setEmpleadosTelefono(datos.getEmpleadosTelefono());
            e.setEmpleadosEmail(datos.getEmpleadosEmail());
            e.setEmpleadosActivo(datos.getEmpleadosActivo());
            return empleadoRepository.save(e);
        });
    }

    public boolean eliminar(Integer id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
