package com.guilherme.portfolio.controller.form;

import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.model.enuns.SituacaoEnum;
import com.guilherme.portfolio.model.enuns.TecnologiasEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProjetoForm {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String descricao;
    @NotNull
    private TecnologiasEnum tecnologias;
    @NotNull
    private SituacaoEnum situacao;
    @NotNull
    private Boolean projetoDeTime;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTecnologias(TecnologiasEnum tecnologias) {
        this.tecnologias = tecnologias;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public void setProjetoDeTime(Boolean projetoDeTime) {
        this.projetoDeTime = projetoDeTime;
    }

    public Projeto converter() {
        return new Projeto(this.nome, this.descricao, this.tecnologias, this.situacao, this.projetoDeTime);
    }
}