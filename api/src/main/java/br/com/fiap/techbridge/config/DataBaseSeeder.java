package br.com.fiap.techbridge.config;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.techbridge.models.Avaliacao;
import br.com.fiap.techbridge.models.Empresa;
import br.com.fiap.techbridge.repository.AvaliacaoRepository;
import br.com.fiap.techbridge.repository.EmpresaRepository;
import br.com.fiap.techbridge.valueobjects.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.techbridge.models.Conta;
import br.com.fiap.techbridge.repository.ContaRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner{

    @Autowired
    ContaRepository contaRepository;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Conta> c = generateContas();
        List<Empresa> e = generateEmpresas();
        List<Avaliacao> a = generateAvaliacoes(c, e);

        contaRepository.saveAll(c);
        empresaRepository.saveAll(e);
        avaliacaoRepository.saveAll(a);
    }
    private static List<Avaliacao> generateAvaliacoes(List<Conta> c, List<Empresa> e){
        List<Avaliacao> avaliacoes = new ArrayList<>();

        avaliacoes.addAll(List.of(
                Avaliacao.builder().nota(1).comentario("Lorem ipsum dolor sit amet")
                        .empresa(e.get(0)).conta(c.get(0)).build(),
                Avaliacao.builder().nota(1).comentario("Não gostei")
                        .empresa(e.get(0)).conta(c.get(1)).build(),
                Avaliacao.builder().nota(2).comentario("Desagradável")
                        .empresa(e.get(0)).conta(c.get(2)).build(),
                Avaliacao.builder().nota(2).comentario("Feio")
                        .empresa(e.get(0)).conta(c.get(3)).build(),
                Avaliacao.builder().nota(5).comentario("Muito bom")
                        .empresa(e.get(0)).conta(c.get(4)).build(),
                Avaliacao.builder().nota(1).comentario("O filme do Pelé é melhor")
                        .empresa(e.get(1)).conta(c.get(0)).build(),
                Avaliacao.builder().nota(1).comentario("Horrível")
                        .empresa(e.get(1)).conta(c.get(1)).build(),
                Avaliacao.builder().nota(2).comentario("De bom só os funcionários bonitos")
                        .empresa(e.get(1)).conta(c.get(2)).build(),
                Avaliacao.builder().nota(2).comentario("")
                        .empresa(e.get(1)).conta(c.get(3)).build(),
                Avaliacao.builder().nota(5).comentario("")
                        .empresa(e.get(1)).conta(c.get(4)).build(),
                Avaliacao.builder().nota(3).comentario("")
                        .empresa(e.get(2)).conta(c.get(0)).build(),
                Avaliacao.builder().nota(3).comentario("")
                        .empresa(e.get(2)).conta(c.get(1)).build(),
                Avaliacao.builder().nota(4).comentario("")
                        .empresa(e.get(2)).conta(c.get(2)).build(),
                Avaliacao.builder().nota(4).comentario("")
                        .empresa(e.get(2)).conta(c.get(3)).build(),
                Avaliacao.builder().nota(5).comentario("")
                        .empresa(e.get(2)).conta(c.get(4)).build(),
                Avaliacao.builder().nota(3).comentario("")
                        .empresa(e.get(3)).conta(c.get(0)).build(),
                Avaliacao.builder().nota(3).comentario("")
                        .empresa(e.get(3)).conta(c.get(1)).build(),
                Avaliacao.builder().nota(4).comentario("")
                        .empresa(e.get(3)).conta(c.get(2)).build(),
                Avaliacao.builder().nota(4).comentario("")
                        .empresa(e.get(3)).conta(c.get(3)).build(),
                Avaliacao.builder().nota(5).comentario("")
                        .empresa(e.get(3)).conta(c.get(4)).build()
        ));
        return avaliacoes;
    }
    private static List<Empresa> generateEmpresas(){
        List<Empresa> empresas = new ArrayList<>();

        Endereco endereco = Endereco.builder()
                .rua("Av. Lins de Vasconselos")
                .numero("1222")
                .bairro("Cambuci")
                .cidade("São Paulo")
                .estado("SP")
                .pais("Brasil")
                .cep("01538-001")
                .build();

        empresas.addAll(List.of(
                Empresa.builder()
                        .endereco(endereco)
                        .nome("FIAP - Aclimação")
                        .razaoSocial("VSTP Educacao S.A.")
                        .ramo("Instituição acadêmica")
                        .cnpj("11.319.526/0007-40")
                        .build(),
                Empresa.builder()
                        .nome("Mercearia do Zé")
                        .ramo("Varejo alimentício")
                        .build(),
                Empresa.builder()
                        .nome("Mercadinho do Manuel")
                        .ramo("Varejo alimentício")
                        .build(),
                Empresa.builder()
                        .nome("Cabeleireiro")
                        .ramo("Beleza")
                        .build(),
                Empresa.builder()
                        .nome("Salão de beleza")
                        .ramo("Beleza")
                        .build()
        ));
        return empresas;
    }
    private static List<Conta> generateContas(){
        List<Conta> contas = new ArrayList<>();
        contas.addAll(List.of(
                Conta.builder()
                .email("example@example.com")
                .senha("12345678")
                .nome("Fulano")
                .build(),
                Conta.builder()
                .email("two@example.com")
                .senha("87654321")
                .nome("Deltrano")
                .build(),
                Conta.builder()
                .email("three@example.com")
                .senha("minhasenha")
                .nome("Beltrano")
                .build(),
                Conta.builder()
                .email("four@example.com")
                .senha("password")
                .nome("Robson")
                .build(),
            Conta.builder()
                .email("five@example.com")
                .senha("anotherpassword")
                .nome("Lucas")
                .build()
                ));
        return contas;
    }
}
