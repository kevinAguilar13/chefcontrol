package com.chefcontrol.services;

import com.chefcontrol.models.Categoria;
import com.chefcontrol.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obtenerPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Categoria crear(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> actualizar(Integer id, Categoria datos) {
        return categoriaRepository.findById(id).map(c -> {
            c.setCategoriasNombre(datos.getCategoriasNombre());
            c.setCategoriasDescripcion(datos.getCategoriasDescripcion());
            c.setCategoriasActiva(datos.getCategoriasActiva());
            return categoriaRepository.save(c);
        });
    }

    public boolean eliminar(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
