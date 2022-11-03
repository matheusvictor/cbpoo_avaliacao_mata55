package models;

import java.util.HashSet;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Congresso {

    private HashSet<Participante> participantes;
    private Scanner scanner = new Scanner(System.in);

    public Congresso() {
        this.participantes = new HashSet<>();
    }


    public void imprimirMenu() {
        // TODO
    }

    public void inscreverParticipantes() {
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

    private LocalDate converterStringDataNascimentoParaLocalDate(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataNascimento, formatter);
    }

}
