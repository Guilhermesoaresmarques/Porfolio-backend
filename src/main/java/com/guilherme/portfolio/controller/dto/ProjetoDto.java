package com.guilherme.portfolio.controller.dto;

import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.model.enuns.TecnologiasEnum;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class ProjetoDto {

    private final Long id;
    private String nome;
    private final String descricao;
    private final TecnologiasEnum tecnologias;
    private final LocalDateTime dataCriacao;

    public ProjetoDto(Projeto projeto) {
        this.id = projeto.getId();
        this.nome = projeto.getNome();
        this.descricao = projeto.getDescricao();
        this.tecnologias = projeto.getTecnologias();
        this.dataCriacao = projeto.getDataCriacao();

    }

    public static Page<ProjetoDto> converter(Page<Projeto> projetos) {
        return projetos.map(ProjetoDto::new);
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public TecnologiasEnum getTecnologias() {
        return tecnologias;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

}