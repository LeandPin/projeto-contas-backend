package com.leandeson.Contas.Contabeis.controllers;

import com.leandeson.Contas.Contabeis.DTO.LancamentoDTO;
import com.leandeson.Contas.Contabeis.entities.Lancamento;
import com.leandeson.Contas.Contabeis.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService; // Injeta a camada de serviço

    // CREATE - Cadastrar um novo lançamento
    @PostMapping
    public ResponseEntity<Lancamento> createLancamento(@RequestBody LancamentoDTO lancamentoDTO) {
        Lancamento novoLancamento = lancamentoService.create(lancamentoDTO);
        // Retorna o objeto criado e o status HTTP 201 Created
        return ResponseEntity.status(201).body(novoLancamento);
    }

    // READ - Visualizar todos os lançamentos
    @GetMapping
    public ResponseEntity<List<Lancamento>> getAllLancamentos() {
        List<Lancamento> lancamentos = lancamentoService.findAll();
        return ResponseEntity.ok(lancamentos);
    }

    // READ - Visualizar um lançamento pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> getLancamentoById(@PathVariable Long id) {
        // O serviço irá tratar o caso de não encontrar o lançamento
        Lancamento lancamento = lancamentoService.findById(id);
        return ResponseEntity.ok(lancamento);
    }

    // UPDATE - Editar um lançamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> updateLancamento(@PathVariable Long id, @RequestBody LancamentoDTO lancamentoDTO) {
        Lancamento lancamentoAtualizado = lancamentoService.update(id, lancamentoDTO);
        return ResponseEntity.ok(lancamentoAtualizado);
    }

    // DELETE - Excluir um lançamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLancamento(@PathVariable Long id) {
        lancamentoService.delete(id);
        // Retorna uma resposta vazia com status HTTP 204 No Content
        return ResponseEntity.noContent().build();
    }
}
