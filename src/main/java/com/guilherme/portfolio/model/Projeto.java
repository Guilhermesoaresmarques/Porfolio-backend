package com.guilherme.portfolio.model;

import com.guilherme.portfolio.model.enuns.SituacaoEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Projeto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private SituacaoEnum situacao;
    private Boolean projetoDeTime;

    public Projeto(String nome, String descricao, SituacaoEnum situacao, Boolean projetoDeTime) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = LocalDateTime.now();
        this.situacao = situacao;
        this.projetoDeTime = projetoDeTime;
    }

    public Projeto() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }


    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public Boolean getProjetoDeTime() {
        return projetoDeTime;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}