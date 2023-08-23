package br.com.fiap.techbridge.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.techbridge.models.Avaliacao;
import br.com.fiap.techbridge.repository.AvaliacaoRepository;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("techbridge/api/avaliacao")
@Slf4j
public class AvaliacaoController {
    
    @Autowired
    AvaliacaoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    private Avaliacao getAvaliacao(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));
    }
    @PostMapping()
    public ResponseEntity<EntityModel<Avaliacao>> create(@RequestBody @Valid Avaliacao avaliacao, BindingResult result){
        /* preciso impedir alguem de criar duas avaliações da mesma empresa */
        repository.save(avaliacao);
        return ResponseEntity
                .created(avaliacao.toEntityModel().getRequiredLink("self").toUri())
                .body(avaliacao.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Avaliacao> show(@PathVariable Long id){
        var avaliacao = getAvaliacao(id);
        return avaliacao.toEntityModel();
    }

    @GetMapping()
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String empresa,
                                                 @RequestParam(required = false) String conta,
                                                 @PageableDefault(size=5) Pageable pageable){
        log.info(empresa, conta);
        Page<Avaliacao> avaliacoes = empresa == null
                ? conta == null
                ? repository.findAll(pageable)
                : repository.findByContaId(conta, pageable)
                : repository.findByEmpresaId(empresa, pageable);
        return assembler.toModel(avaliacoes.map(Avaliacao::toEntityModel));
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Avaliacao>> update(@PathVariable Long id, @RequestBody @Valid Avaliacao avaliacao){
        var avaliacaoEncontrada = repository.findById(id);
        if (avaliacaoEncontrada.isEmpty())
            return ResponseEntity.notFound().build();
        avaliacao.setJulgamentoPositivo(avaliacaoEncontrada.get().getJulgamentoPositivo());
        avaliacao.setJulgamentoNegativo(avaliacaoEncontrada.get().getJulgamentoNegativo());
        avaliacao.setConta(avaliacaoEncontrada.get().getConta());
        avaliacao.setId(id);
        repository.save(avaliacao);
        return ResponseEntity.ok(avaliacao.toEntityModel());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Avaliacao> destroy(@PathVariable Long id){
        var avaliacaoEncontrada = getAvaliacao(id);
        repository.delete(avaliacaoEncontrada);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/{contaId}")
    public String julgar(){
        return "ainda não implementado";
    }
}
