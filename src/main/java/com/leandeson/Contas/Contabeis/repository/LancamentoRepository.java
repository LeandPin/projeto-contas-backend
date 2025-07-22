package com.leandeson.Contas.Contabeis.repository;

import com.leandeson.Contas.Contabeis.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
