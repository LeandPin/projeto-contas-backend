package com.leandeson.Contas.Contabeis.service;

import com.leandeson.Contas.Contabeis.entities.Categoria;
import com.leandeson.Contas.Contabeis.exceptions.ResourceNotFoundException;
import com.leandeson.Contas.Contabeis.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }


    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));
    }


    public Categoria create(Categoria categoria) {
        // Garante que o ID seja nulo para criar um novo registro
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }


    public Categoria update(Long id, Categoria categoriaAtualizacao) {
        // Busca a categoria existente no banco
        Categoria categoriaExistente = findById(id);

        // Atualiza os campos (neste caso, apenas o nome)
        categoriaExistente.setNome(categoriaAtualizacao.getNome());

        // Salva a entidade atualizada
        return categoriaRepository.save(categoriaExistente);
    }

    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada com id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
