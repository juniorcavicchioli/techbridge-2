package br.com.fiap.techbridge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.techbridge.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    public List<Conta> findByEmail(String email);
}
