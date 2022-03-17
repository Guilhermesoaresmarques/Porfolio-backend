package com.guilherme.portfolio.controller;

import com.guilherme.portfolio.controller.dto.ProjetoDto;
import com.guilherme.portfolio.controller.form.ProjetoAtualizadoForm;
import com.guilherme.portfolio.controller.form.ProjetoForm;
import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;

    @GetMapping
    @Cacheable(value = "projetos")
    public Page<ProjetoDto> projetos(
            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 2)Pageable paginacao) {

        Page<Projeto> projetos = repository.findAll(paginacao);
        return ProjetoDto.converter(projetos);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "projetos")
    public ResponseEntity<ProjetoDto> projetoExpecifico(@PathVariable Long id) {
        Optional<Projeto> projeto = repository.findById(id);
        return projeto.map(value -> ResponseEntity.ok(
                new ProjetoDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "projetos", allEntries = true)
    public ResponseEntity<ProjetoDto> novoProjeto(@RequestBody @Valid ProjetoForm form, UriComponentsBuilder uriBuider) {
        Projeto projeto = form.converter();
        repository.save(projeto);

        URI uri = uriBuider.path("/projeto/{id}").buildAndExpand(projeto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProjetoDto(projeto));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "projetos", allEntries = true)
    public ResponseEntity<ProjetoDto> atualizarProjeto(@PathVariable Long id, @RequestBody @Valid ProjetoAtualizadoForm form) {
        Optional<Projeto> projetoProcurado = repository.findById(id);
        if(projetoProcurado.isPresent()) {
            Projeto projeto = form.atualizar(id, repository);
            return ResponseEntity.ok(new ProjetoDto(projeto));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "projetos", allEntries = true)
    public ResponseEntity<?> removerProjeto(@PathVariable Long id) {
        Optional<Projeto> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}