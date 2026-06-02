package com.chefcontrol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chefcontrol.models.Categoria;
import com.chefcontrol.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoria) {

        Categoria existente =
                categoriaRepository.findById(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setNombre(categoria.getNombre());

        return categoriaRepository.save(existente);
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}