package com.guilherme.portfolio.service;

import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.model.dto.ProjetoDto;
import com.guilherme.portfolio.model.form.ProjetoAtualizadoForm;
import com.guilherme.portfolio.model.form.ProjetoForm;
import com.guilherme.portfolio.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository repository;

    @Override
    public ResponseEntity<ProjetoDto> getProjeto(Long id) {
        Optional<Projeto> projeto = repository.findById(id);
        return projeto.map(value -> ResponseEntity.ok(
                new ProjetoDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
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
