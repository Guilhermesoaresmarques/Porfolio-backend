package com.guilherme.portfolio.model.form;

import com.guilherme.portfolio.model.Projeto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProjetoAtualizadoForm {

    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    private String descricao;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Projeto converter(Projeto projeto) {

        projeto.setNome(this.nome);
        projeto.setDescricao(this.descricao);

        return projeto;
    }
}