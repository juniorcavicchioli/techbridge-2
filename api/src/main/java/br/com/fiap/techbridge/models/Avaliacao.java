package br.com.fiap.techbridge.models;

import br.com.fiap.techbridge.controllers.AvaliacaoController;
import br.com.fiap.techbridge.controllers.ContaController;
import br.com.fiap.techbridge.controllers.EmpresaController;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Avaliacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao", nullable = false)
    private Long id;
    @NotNull(message = "O id da conta que está avaliando é obrigatório")
    @Min(value=1, message = "A nota deve ter um valor entre 1 e 5")
    @Max(value=5, message = "A nota deve ter um valor entre 1 e 5")
    @Column(length = 1)
    private int nota;
    @Column(length = 500)
    private String comentario;
    @Column(length = 6)
    private int julgamentoPositivo = 0;
    @Column(length = 6)
    private int julgamentoNegativo = 0;

    @ManyToOne
    @NotNull(message = "O id da empresa que está sendo avaliada é obrigatório")
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;
    
    @ManyToOne
    @NotNull(message = "O id da conta que está avaliadando é obrigatório")
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;

    public EntityModel<Avaliacao> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(AvaliacaoController.class).show(id)).withSelfRel(),
                linkTo(methodOn(AvaliacaoController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(AvaliacaoController.class).index(null, null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(ContaController.class).show(this.getConta().getId())).withRel("conta"),
                linkTo(methodOn(EmpresaController.class).show(this.getEmpresa().getId())).withRel("empresa")

        );
    }
}
