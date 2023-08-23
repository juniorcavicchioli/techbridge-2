package br.com.fiap.techbridge.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.techbridge.models.Conta;
import br.com.fiap.techbridge.repository.ContaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("techbridge/api/conta")
@Slf4j
public class ContaController {

    @Autowired
    ContaRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    private Conta getConta(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }
    @PostMapping()
    public ResponseEntity<EntityModel<Conta>> signup(@RequestBody @Valid Conta conta, BindingResult result){
        repository.save(conta);
        return ResponseEntity
                .created(conta.toEntityModel().getRequiredLink("self").toUri())
                .body(conta.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Conta> show(@PathVariable Long id){
        var conta = getConta(id);
        return conta.toEntityModel();
    }

    @GetMapping()
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 5) Pageable pageable){ //@RequestParam String busca
        Page<Conta> contas = repository.findAll(pageable);
        return assembler.toModel(contas.map(Conta::toEntityModel));
    }

    @PutMapping("{id}")
    public EntityModel<Conta> update(@PathVariable Long id, @RequestBody @Valid Conta conta, BindingResult result){
        getConta(id);
        conta.setId(id);
        repository.save(conta);
        return conta.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Conta> destroy(@PathVariable Long id){
        var contaEncontrada = getConta(id);
        repository.delete(contaEncontrada);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public String login(){
        return "Ainda não implementado";
    }

}
