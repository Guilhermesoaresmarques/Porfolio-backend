package com.guilherme.portfolio.controller;

import com.guilherme.portfolio.model.Projeto;
import com.guilherme.portfolio.model.dto.ProjetoDto;
import com.guilherme.portfolio.model.form.ProjetoAtualizadoForm;
import com.guilherme.portfolio.model.form.ProjetoForm;
import com.guilherme.portfolio.repository.ProjetoRepository;
import com.guilherme.portfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/projetos/v1")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private ProjetoService service;

    @GetMapping
    @Cacheable(value = "projetos")
    public Page<ProjetoDto> projetos(
            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, size = 2)Pageable paginacao) {

        Page<Projeto> projetos = service.getAllProjetos(paginacao);
        return ProjetoDto.converter(projetos);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "projetos")
    public ResponseEntity<ProjetoDto> projetoExpecifico(@PathVariable Long id) {
        Optional<Projeto> projeto = service.getProjeto(id);
        return projeto.map(value -> ResponseEntity.ok(
                new ProjetoDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "projetos", allEntries = true)
    public ResponseEntity<ProjetoDto> novoProjeto(@RequestBody @Valid ProjetoForm form, UriComponentsBuilder uriBuider) {
        Projeto projeto = service.saveProjeto(form);

        URI uri = uriBuider.path("/projeto/{id}").buildAndExpand(projeto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProjetoDto(projeto));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "projetos", allEntries = true)
    public void atualizarProjeto(@PathVariable Long id,
                                 @RequestBody @Valid ProjetoAtualizadoForm form) {

        Optional<Projeto> projeto = service.getProjeto(id);

        projeto.map( projetoExiste -> service.updateProjeto(form, id))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto nao encontrado"));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "projetos", allEntries = true)
    public ResponseEntity<?> removerProjeto(@PathVariable Long id) {
        Optional<Projeto> projeto = repository.findById(id);
        if (projeto.isPresent()) {
            service.deleteProjeto(id);
            return ResponseEntity.ok("Projeto deletado");
        }

        return ResponseEntity.badRequest().build();
    }
}