package br.com.fiap.techbridge.repository;

import br.com.fiap.techbridge.models.Empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    public Empresa findFirstByRazaoSocial(String razaoSocial);

    Page<Empresa> findByRamo(String ramo, Pageable pageable);
}
