package com.guilherme.portfolio.model.form;

import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.model.enuns.TecnologiasEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProjetoAtualizadoForm {

    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    private String descricao;

    @NotNull
    private TecnologiasEnum tecnologias;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTecnologias(TecnologiasEnum tecnologias) {
        this.tecnologias = tecnologias;
    }

    public Projeto converter(Projeto projeto) {

        projeto.setNome(this.nome);
        projeto.setDescricao(this.descricao);
        projeto.setTecnologias(this.tecnologias);

        return projeto;
    }
}