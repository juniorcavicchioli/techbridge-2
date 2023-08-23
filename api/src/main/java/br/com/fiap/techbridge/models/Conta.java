package br.com.fiap.techbridge.models;

import br.com.fiap.techbridge.controllers.AvaliacaoController;
import br.com.fiap.techbridge.controllers.ContaController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta", nullable = false)
    private Long id;
    @NotBlank(message = "O e-mail deve ser preenchido")
    @Column(length = 50)
    private String email;
    @NotBlank(message = "A senha tem que ter ao menos 8 caracteres")
    @Size(min = 8, message = "A senha tem que ter ao menos 8 caracteres")
    //@JsonIgnore
    @Column(length = 50)
    private String senha;
    @NotBlank(message = "O nome deve ser preenchido")
    @Column(length = 50)
    private String nome;

    public EntityModel<Conta> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ContaController.class).show(id)).withSelfRel(),
                linkTo(methodOn(ContaController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(ContaController.class).index(Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(AvaliacaoController.class).index(null, String.valueOf(id), Pageable.unpaged())).withRel("avaliacoes")
        );
    }
//    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
//    private List<Avaliacao> avaliacoes = new ArrayList<>();
}
