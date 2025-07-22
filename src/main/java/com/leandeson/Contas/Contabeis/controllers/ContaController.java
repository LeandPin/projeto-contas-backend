package com.leandeson.Contas.Contabeis.controllers;

import com.leandeson.Contas.Contabeis.DTO.LancamentoDTO;
import com.leandeson.Contas.Contabeis.entities.Conta;
import com.leandeson.Contas.Contabeis.entities.Lancamento;
import com.leandeson.Contas.Contabeis.service.ContaService;
import com.leandeson.Contas.Contabeis.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaController {
    @Autowired
    private ContaService contaService;

    // CREATE - Cadastrar uma nova conta
    @PostMapping
    public ResponseEntity<Conta> createConta(@RequestBody Conta conta) { // <-- MUDANÇA AQUI
        Conta novaConta = contaService.create(conta);
        return ResponseEntity.status(201).body(novaConta);
    }

    // READ - Visualizar todas as contas
    @GetMapping
    public ResponseEntity<List<Conta>> getAllContas() {
        List<Conta> contas = contaService.findAll();
        return ResponseEntity.ok(contas);
    }

    // READ - Visualizar uma conta pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContaById(@PathVariable Long id) {
        Conta conta = contaService.findById(id);
        return ResponseEntity.ok(conta);
    }

    // UPDATE - Editar uma conta existente
    @PutMapping("/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable Long id, @RequestBody Conta conta) { // <-- MUDANÇA AQUI
        Conta contaAtualizada = contaService.update(id, conta);
        return ResponseEntity.ok(contaAtualizada);
    }

    // DELETE - Excluir uma conta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
