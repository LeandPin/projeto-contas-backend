package com.leandeson.Contas.Contabeis.service;


import com.leandeson.Contas.Contabeis.DTO.LancamentoDTO;
import com.leandeson.Contas.Contabeis.entities.Categoria;
import com.leandeson.Contas.Contabeis.entities.Conta;
import com.leandeson.Contas.Contabeis.entities.Lancamento;
import com.leandeson.Contas.Contabeis.exceptions.ResourceNotFoundException;
import com.leandeson.Contas.Contabeis.repository.CategoriaRepository;
import com.leandeson.Contas.Contabeis.repository.ContaRepository;
import com.leandeson.Contas.Contabeis.repository.LancamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<Lancamento> findAll() {
        return lancamentoRepository.findAll();
    }


    public Lancamento findById(Long id) {
        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado com id: " + id));
    }


    public Lancamento create(LancamentoDTO dto) {
        // AJUSTE: Usando getters para buscar os IDs
        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com id: " + dto.getContaId()));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + dto.getCategoriaId()));

        Lancamento novoLancamento = new Lancamento();

        // AJUSTE: Usando getters para popular o novo objeto
        novoLancamento.setDescricao(dto.getDescricao());
        novoLancamento.setValor(dto.getValor());
        novoLancamento.setData(dto.getData());
        novoLancamento.setTipo(dto.getTipo());
        novoLancamento.setConta(conta);
        novoLancamento.setCategoria(categoria);

        return lancamentoRepository.save(novoLancamento);
    }


    public Lancamento update(Long id, LancamentoDTO dto) {
        Lancamento lancamentoExistente = findById(id);

        // AJUSTE: Usando getters para buscar as entidades
        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com id: " + dto.getContaId()));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + dto.getCategoriaId()));

        // AJUSTE: Usando getters para atualizar o objeto existente
        lancamentoExistente.setDescricao(dto.getDescricao());
        lancamentoExistente.setValor(dto.getValor());
        lancamentoExistente.setData(dto.getData());
        lancamentoExistente.setTipo(dto.getTipo());
        lancamentoExistente.setConta(conta);
        lancamentoExistente.setCategoria(categoria);

        return lancamentoRepository.save(lancamentoExistente);
    }

    public void delete(Long id) {
        if (!lancamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lançamento não encontrado com id: " + id);
        }
        lancamentoRepository.deleteById(id);
    }
}
