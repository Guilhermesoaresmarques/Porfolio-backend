package com.guilherme.portfolio.service;

import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.model.form.ProjetoAtualizadoForm;
import com.guilherme.portfolio.model.form.ProjetoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProjetoService {

    Optional<Projeto> getProjeto(Long id);
    Page<Projeto> getAllProjetos(Pageable pageable);
    Projeto saveProjeto(ProjetoForm projeto);
    Optional<Projeto> updateProjeto(ProjetoAtualizadoForm form, Long id);
    void deleteProjeto(Long id);
}
