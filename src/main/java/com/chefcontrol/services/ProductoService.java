package com.chefcontrol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefcontrol.models.Producto;
import com.chefcontrol.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto producto) {

        Producto existente = productoRepository.findById(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setTiempoPreparacion(producto.getTiempoPreparacion());
        existente.setDisponible(producto.getDisponible());
        existente.setCategoriaId(producto.getCategoriaId());

        return productoRepository.save(existente);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}