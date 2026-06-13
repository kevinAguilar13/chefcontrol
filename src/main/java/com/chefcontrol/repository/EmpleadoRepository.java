package com.chefcontrol.repository;

import com.chefcontrol.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByEmpleadosActivoTrue();
    List<Empleado> findByEmpleadosRol(Empleado.RolEmpleado rol);
}
