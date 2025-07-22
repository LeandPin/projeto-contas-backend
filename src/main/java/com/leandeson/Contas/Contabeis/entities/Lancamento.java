package com.leandeson.Contas.Contabeis.entities;

import com.leandeson.Contas.Contabeis.enums.TipoLancamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "lancamentos")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor; // Usar BigDecimal para valores monetários

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @ManyToOne // Muitos Lançamentos podem pertencer a UMA Conta
    @JoinColumn(name = "conta_id") // Nome da coluna de chave estrangeira no banco
    private Conta conta;

    @ManyToOne // Muitos Lançamentos podem pertencer a UMA Categoria
    @JoinColumn(name = "categoria_id") // Nome da coluna de chave estrangeira
    private Categoria categoria;


    public Lancamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
