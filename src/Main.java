import models.Pessoa;
import models.Congresso;
import models.Participante;
import models.especialistas.Revisor;
import models.organizadores.GeneralChair;
import models.organizadores.ProgramChair;

import exceptions.ParticipanteNaoEncontradoException;

import java.util.Scanner;
import java.time.LocalDate;

import static services.ConversorData.converterDataParaLocalDate;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Congresso congresso = Congresso.getInstance();
    private static Pessoa usuarioLogado = null;

    public static void imprimirMenu() {
        Main.imprimirCabecalhoMenu();
        System.out.println("============================================================");
        if (usuarioLogado == null) {
            System.out.println("1. Fazer login");
            System.out.println("2. Inscrever-me");
        } else {

            if (usuarioLogado instanceof GeneralChair) {
                System.out.println("3. Validar inscrição");
                System.out.println("4. Emitir certificado");
                System.out.println("5. Cadastrar organizadores");
            } else if (usuarioLogado instanceof ProgramChair) {
                System.out.println("6. Avaliar artigo");
                System.out.println("7. Rejeitar artigo");
            } else if (usuarioLogado instanceof Revisor) {
                System.out.println("8. Enviar avaliação de artigo");
            }

            System.out.println("9. Submeter artigo");
            System.out.println("10. Ver avaliações de um artigo");
            System.out.println("11. Listar artigos aceitos em ordem alfabética");
            System.out.println("12. Listar artigos negados em ordem alfabética");
            System.out.println("13. Ver dados de um artigo");
            System.out.println("14. Listar participantes em ordem alfabética");
        }

        System.out.println("0. Fazer log-out");
        System.out.println("99. Encerrar o programa");
//        System.out.println("Ou aperte qualquer outra tecla para voltar ao menu principal");
        System.out.println("============================================================");

        System.out.print("Digite uma opção: ");
    }

    public static void imprimirCabecalhoMenu() {
        if (usuarioLogado instanceof ProgramChair) {
            System.out.println("CARGO: Program Chair");
        } else if (usuarioLogado instanceof GeneralChair) {
            System.out.println("CARGO: General Chair");
        }
        if (usuarioLogado != null) {
            System.out.println("Boas-vindas, " + usuarioLogado.getNome());
        }
    }

    public static void imprimirMenuLogin() {

        if (usuarioLogado == null) {
            try {
                System.out.println("============================================================");
                System.out.print("Digite o CPF: ");
                String cpf = scanner.next();
                System.out.print("Digite a senha: ");
                String senha = scanner.next();
                usuarioLogado = Main.congresso.fazerLogin(cpf, senha);
            } catch (ParticipanteNaoEncontradoException exception) {
                System.err.println(exception.getMessage());
            }
        } else {
            usuarioLogado = null;
            System.out.println("Usuário Deslogado Corretamente");
        }
        System.out.println("============================================================");

    }

    public static void imprimirMenuCadastro() {

        System.out.print("Digite seu CPF: ");
        String cpf = scanner.next();
        System.out.print("Digite seu nome: ");
        String nome = scanner.next();
        System.out.print("Digite sua senha: ");
        String senha = scanner.next();

        LocalDate dataNascimentoFormatada = null;
        while (true) {
            System.out.print("Digite sua data de nascimento (dd/MM/aaaa): ");
            String dataNascimento = scanner.next();
            try {
                dataNascimentoFormatada = converterDataParaLocalDate(dataNascimento);
                break;
            } catch (Exception exception) {
                System.out.println("Data inválida!");
            }
        }

//        System.out.print("Digite sua titulação acadêmica: ");
//        String titulacaoAcademica = scanner.next();
//        System.out.print("Digite o nome da instituição na qual está vinculada(o): ");
//        String instituicaoDeVinculo = scanner.next();
//
//        Participante participante;
//        if (usuarioLogado instanceof GeneralChair) {
//            String categoria;
//            if (categoria == "GC") {
//                participante = new GeneralChair(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
//            } else {
//                participante = new ProgramChair(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
//            }
//        } else {
//            participante = new Participante(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
//        }

//        congresso.addParticipante(participante);
    }


    public static void main(String[] args) {
        GeneralChair admin = new GeneralChair(
                "123",
                "admin",
                "admin",
                LocalDate.now(),
                "Manda Chuva",
                "Administrador do sistema"
        );
        congresso.addParticipante(admin);

        int opcao;
        do {
            Main.imprimirMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> Main.imprimirMenuLogin();
                case 0 -> {
                    System.out.println("Programa encerrado!");
                    System.exit(0);
                }
            }
        } while (opcao != 15);


//
//
//        Participante p1;
//
//        p1 = congresso.fazerLogin("123.456.789-00", "1234");
//        if (p1 == null) {
//            // System.out.println("Deseja se cadastrar?");
//            System.out.print("Digite seu CPF: ");
//            String cpf = scanner.next();
//            System.out.print("Digite seu nome: ");
//            String nome = scanner.next();
//            System.out.print("Digite sua senha: ");
//            String senha = scanner.next();
//            System.out.print("Digite sua data de nascimento (dd/MM/aaaa): ");
//            String dataNascimento = scanner.next();
//            System.out.print("Digite sua titulação acadêmica: ");
//            String titulacaoAcademica = scanner.next();
//            System.out.print("Digite o nome da instituição na qual está vinculada(o): ");
//            String instituicaoDeVinculo = scanner.next();
//            p1 = congresso.inscreverParticipante(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
//        } else {
//            System.out.println("Boas-vindas, " + p1.getNome());
//        }
//
//        Participante p2 = congresso.inscreverParticipante("123", "Roberta", "", "08/11/2022", "", "");
//        congresso.verDadosDeArtigo(1);
//
//        congresso.listarParticipantesEmOrdemAlfabetica();

    }

}
