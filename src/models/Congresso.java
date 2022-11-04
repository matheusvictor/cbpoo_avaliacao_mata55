package models;

import models.participante.Participante;
import models.participante.especialista.Autor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static services.ConversorDeDataService.converterStringDataNascimentoParaLocalDate;

public class Congresso {

    private ArrayList<Participante> participantes;
    private final Scanner scanner = new Scanner(System.in);

    private static Congresso congresso;

    public static Congresso getInstance() {
        if (congresso == null) {
            congresso = new Congresso();
        }
        return congresso;
    }

    private Congresso() {
        this.participantes = new ArrayList<>();
    }

    public void imprimirMenu() {
        // TODO
    }

    public void inscreverParticipante() {
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.next();
        System.out.print("Digite seu nome: ");
        String nome = scanner.next();
        System.out.print("Digite sua senha: ");
        String senha = scanner.next();
        System.out.print("Digite sua data de nascimento (dd/MM/aaaa): ");
        String dataNascimento = scanner.next();
        System.out.print("Digite sua titulação acadêmica: ");
        String titulacaoAcademica = scanner.next();
        System.out.print("Digite o nome da instituição na qual está vinculada(o): ");
        String instituicaoDeVinculo = scanner.next();

        LocalDate dataNascimentoFormatada = converterStringDataNascimentoParaLocalDate(dataNascimento);

        Participante p = new Participante(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
        this.participantes.add(p);
    }

    public Autor inscreverAutor() {
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.next();
        System.out.print("Digite seu nome: ");
        String nome = scanner.next();
        System.out.print("Digite sua senha: ");
        String senha = scanner.next();
        System.out.print("Digite sua data de nascimento (dd/MM/aaaa): ");
        String dataNascimento = scanner.next();
        System.out.print("Digite sua titulação acadêmica: ");
        String titulacaoAcademica = scanner.next();
        System.out.print("Especialidade: ");
        String especialidade = scanner.next();
        System.out.print("Digite o nome da instituição na qual está vinculada(o): ");
        String instituicaoDeVinculo = scanner.next();

        LocalDate dataNascimentoFormatada = converterStringDataNascimentoParaLocalDate(dataNascimento);

        Autor a = new Autor(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo, especialidade);
        this.participantes.add(a);
        return a;
    }

    public void inscreverParticipanteMock() {
        LocalDate dataNascimentoFormatada = converterStringDataNascimentoParaLocalDate("20/03/2000");
        Participante mock = new Participante("123.456.789-00", "Mock", "1234", dataNascimentoFormatada, "Mock", "instituicaoDeVinculo");
        this.participantes.add(mock);
    }

    public void submeterArtigo(Autor autor) {

        Autor a = inscreverAutor();

        HashSet<Autor> autoresDoArtigo = new HashSet<>();

        autoresDoArtigo.add(a);

        System.out.print("Título do artigo: ");
        String titulo = scanner.next();
        System.out.print("Resumo do artigo: ");
        String resumo = scanner.next();

        List<String> palavrasChave = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.print(i + "ª palavra-chave: ");
            String palavraChave = scanner.next();
            palavrasChave.add(palavraChave);
        }

        System.out.print("Quantidade de páginas: ");
        int quantidadeDePaginas = scanner.nextInt();

        Artigo artigo = new Artigo(titulo, resumo, palavrasChave, quantidadeDePaginas, autoresDoArtigo);
        System.out.println(artigo);

    }

    public Autor localizarAutor(String cpf) {

        if (!this.participantes.isEmpty()) {
            for (Participante p : this.participantes) {
                if (p.getCpf().equals(cpf)) {
                    System.out.println("Achei");
                    return (Autor) p;
                }
            }
        }
        return null;
    }

}
