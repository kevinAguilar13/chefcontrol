package com.chefcontrol.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chefcontrol.models.Producto;
import com.chefcontrol.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
}