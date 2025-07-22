package com.leandeson.Contas.Contabeis.service;

import com.leandeson.Contas.Contabeis.entities.Conta;
import com.leandeson.Contas.Contabeis.exceptions.ResourceNotFoundException;
import com.leandeson.Contas.Contabeis.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;


    public List<Conta> findAll() {
        return contaRepository.findAll();
    }


    public Conta findById(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com id: " + id));
    }


    public Conta create(Conta conta) {
        // Recebe a entidade Conta diretamente
        // Garantir que o ID seja nulo para criar um novo registro
        conta.setId(null);
        return contaRepository.save(conta);
    }


    public Conta update(Long id, Conta contaAtualizacao) {
        // Busca a conta existente no banco para garantir que ela existe
        Conta contaExistente = findById(id);

        // Atualiza os campos da conta existente com os dados da conta recebida
        contaExistente.setNome(contaAtualizacao.getNome());
        contaExistente.setTipo(contaAtualizacao.getTipo());

        // Salva a entidade atualizada
        return contaRepository.save(contaExistente);
    }

    public void delete(Long id) {
        if (!contaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Conta não encontrada com id: " + id);
        }
        contaRepository.deleteById(id);
    }
}
