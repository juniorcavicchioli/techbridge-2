package br.com.fiap.techbridge.valueobjects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Endereco {

    @Column(length = 100)
    private String rua;
    @Column(length = 10)
    private String numero;
    @Column(length = 50)
    private String complemento;
    @Column(length = 50)
    private String bairro;
    @Column(length = 50)
    private String cidade;
    @Column(length = 2)
    private String estado;
    @Column(length = 50)
    private String pais;
    @Column(length = 9)
    private String cep;
}
