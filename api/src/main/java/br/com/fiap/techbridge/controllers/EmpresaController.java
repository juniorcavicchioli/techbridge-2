package br.com.fiap.techbridge.controllers;

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

import br.com.fiap.techbridge.models.Empresa;
import br.com.fiap.techbridge.repository.EmpresaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("techbridge/api/empresa")
public class EmpresaController {
    
    @Autowired
    EmpresaRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    private Empresa getEmpresa(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
    }
    @PostMapping()
    public ResponseEntity<EntityModel<Empresa>> create(@RequestBody @Valid Empresa empresa, BindingResult result){
        repository.save(empresa);
        return ResponseEntity
                .created(empresa.toEntityModel().getRequiredLink("self").toUri())
                .body(empresa.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Empresa> show(@PathVariable Long id){
        var empresa = getEmpresa(id);
        return empresa.toEntityModel();
    }

    @GetMapping()
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String ramo,
                                                 @PageableDefault(size=5) Pageable pageable){
        Page<Empresa> empresas = (ramo == null) ?
            repository.findAll(pageable) : repository.findByRamo(ramo, pageable);
        return assembler.toModel(empresas.map(Empresa::toEntityModel)); // Reference method - java 14
    }

    @PutMapping("{id}")
    public EntityModel<Empresa> update(@PathVariable Long id, @RequestBody @Valid Empresa empresa, BindingResult result){
        getEmpresa(id);
        empresa.setId(id);
        repository.save(empresa); // se aquela entidade já existe com aquele id, ele faz um update
        return empresa.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Empresa> destroy(@PathVariable Long id){
        var empresaEncontrada = getEmpresa(id);
        repository.delete(empresaEncontrada);
        return ResponseEntity.noContent().build();
    }
}
