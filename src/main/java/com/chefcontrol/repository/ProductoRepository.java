package com.chefcontrol.repository;

import com.chefcontrol.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByProductosDisponibleTrue();
    List<Producto> findByCategoria_CategoriasId(Integer categoriaId);
}