import models.Congresso;
import models.Participante;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        Congresso congresso = Congresso.getInstance();
        Participante p1;

        int opcao;
        congresso.imprimirMenu();
//        do {
//            opcao = scanner.nextInt();
//
//            if (opcao == 1) {
//
//            }
//        } while (opcao > 15 || opcao < 1);

        p1 = congresso.fazerLogin("123.456.789-00", "1234");
        if (p1 == null) {
            // System.out.println("Deseja se cadastrar?");
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
            p1 = congresso.inscreverParticipante(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        } else {
            System.out.println("Boas-vindas, " + p1.getNome());
        }

        Participante p2 = congresso.inscreverParticipante("123", "Roberta", "", "08/11/2022", "", "");
        congresso.verDadosDeArtigo(1);

        congresso.listarParticipantesEmOrdemAlfabetica();

    }

}
