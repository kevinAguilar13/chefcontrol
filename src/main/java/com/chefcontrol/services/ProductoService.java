package com.chefcontrol.services;

import com.chefcontrol.models.Producto;
import com.chefcontrol.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarDisponibles() {
        return productoRepository.findByProductosDisponibleTrue();
    }

    public List<Producto> listarPorCategoria(Integer categoriaId) {
        return productoRepository.findByCategoria_CategoriasId(categoriaId);
    }

    public Optional<Producto> obtenerPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizar(Integer id, Producto datos) {
        return productoRepository.findById(id).map(p -> {
            p.setProductosNombre(datos.getProductosNombre());
            p.setProductosDescripcion(datos.getProductosDescripcion());
            p.setProductosPrecio(datos.getProductosPrecio());
            p.setProductosTiempoPreparacion(datos.getProductosTiempoPreparacion());
            p.setProductosDisponible(datos.getProductosDisponible());
            p.setProductosImagenUrl(datos.getProductosImagenUrl());
            p.setCategoria(datos.getCategoria());
            return productoRepository.save(p);
        });
    }

    public boolean eliminar(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}