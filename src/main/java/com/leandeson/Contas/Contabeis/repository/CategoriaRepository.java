package com.leandeson.Contas.Contabeis.repository;

import com.leandeson.Contas.Contabeis.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
