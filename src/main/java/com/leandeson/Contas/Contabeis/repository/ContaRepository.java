package com.leandeson.Contas.Contabeis.repository;

import com.leandeson.Contas.Contabeis.entities.Conta;
import com.leandeson.Contas.Contabeis.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
