package br.com.fiap.techbridge.models;

import br.com.fiap.techbridge.controllers.AvaliacaoController;
import br.com.fiap.techbridge.controllers.EmpresaController;
import br.com.fiap.techbridge.valueobjects.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.ArrayList;
import java.util.List;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", nullable = false)
    private Long id;
    @NotBlank(message = "A empresa tem que possuir um nome")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "razao_social", length = 50)
    private String razaoSocial;
    @NotBlank(message = "A empresa tem que possuir um ramo de atuação")
    @Column(length = 50)
    private String ramo;
    @Column(name = "cnpj", length = 18)
    private String cnpj;

    @Embedded
    private Endereco endereco;

    public EntityModel<Empresa> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(EmpresaController.class).show(id)).withSelfRel(),
                linkTo(methodOn(EmpresaController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(EmpresaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(AvaliacaoController.class).index(String.valueOf(id), null, Pageable.unpaged())).withRel("avaliacoes")
        );
    }

//    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
//    private List<Avaliacao> avaliacoes = new ArrayList<>();
}
