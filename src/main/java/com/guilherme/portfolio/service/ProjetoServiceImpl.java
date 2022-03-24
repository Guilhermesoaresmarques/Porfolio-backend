package com.guilherme.portfolio.service;

import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.model.form.ProjetoAtualizadoForm;
import com.guilherme.portfolio.model.form.ProjetoForm;
import com.guilherme.portfolio.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository repository;

    @Override
    public Projeto getProjeto(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<Projeto> getAllProjetos (Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Projeto saveProjeto(ProjetoForm form) {
        Projeto projeto = form.converter();
        repository.save(projeto);
        return projeto;
    }

    @Override
    public Projeto updateProjeto(ProjetoAtualizadoForm form, Long id) {

        Projeto projetoProcurado = repository.getById(id);
        Projeto projeto = form.converter(projetoProcurado);
        repository.save(projeto);
        return projeto;
    }

    @Override
    public void deleteProjeto(Long id) {
        repository.deleteById(id);
    }
}
