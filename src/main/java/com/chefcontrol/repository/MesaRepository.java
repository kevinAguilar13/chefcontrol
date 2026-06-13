package com.chefcontrol.repository;

import com.chefcontrol.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    List<Mesa> findByMesasEstado(Mesa.EstadoMesa estado);
    List<Mesa> findByMesasActivaTrue();
}
