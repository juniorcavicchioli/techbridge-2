package br.com.fiap.techbridge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.techbridge.models.Avaliacao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

//    @Query("SELECT a FROM Avaliacao a WHERE a.empresa.id = ?1 ") //JPQL
    Page<Avaliacao> findByEmpresaId(String busca, Pageable pageable);

    Page<Avaliacao> findByContaId(String conta, Pageable pageable);
}
