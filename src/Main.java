import exceptions.DataInvalidaException;
import exceptions.ParticipanteNaoEncontradoException;
import models.Congresso;
import models.Participante;
import models.Pessoa;
import models.especialistas.Autor;
import models.especialistas.Revisor;
import models.organizadores.GeneralChair;
import models.organizadores.ProgramChair;

import java.time.LocalDate;
import java.util.Scanner;

import static services.ConversorData.converterDataParaLocalDate;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Congresso congresso = Congresso.getInstance();
    private static Pessoa usuarioLogado = null;

    public static void imprimirCabecalhoMenu() {
        if (usuarioLogado instanceof ProgramChair) {
            System.out.println("CARGO: Program Chair");
        } else if (usuarioLogado instanceof GeneralChair) {
            System.out.println("CARGO: General Chair");
        } else if (usuarioLogado instanceof Revisor) {
            System.out.println("CARGO: Revisor(a) de artigo");
        } else if (usuarioLogado instanceof Autor) {
            System.out.println("CARGO: Autor(a) de artigo");
        }
        if (usuarioLogado != null) {
            System.out.println("Boas-vindas, " + usuarioLogado.getNome());
        }
    }

    public static void imprimirMenu() {
        Main.imprimirCabecalhoMenu();
        System.out.println("============================================================");
        if (usuarioLogado == null) {
            System.out.println("1. Fazer login");
            System.out.println("2. Inscrever-me");
        } else {

            System.out.println("1. Fazer log-out");

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

        System.out.println("99. Encerrar o programa");
//        System.out.println("Ou aperte qualquer outra tecla para voltar ao menu principal");
        System.out.println("============================================================");

        System.out.print("Digite uma opção: ");
    }

    public static void imprimirMenuLogin() {
        if (usuarioLogado == null) {
            try {
                System.out.println("============================================================");
                System.out.print("Digite seu CPF: ");
                String cpf = scanner.next();
                System.out.print("Digite sua senha: ");
                String senha = scanner.next();
                usuarioLogado = congresso.fazerLogin(cpf, senha);
            } catch (ParticipanteNaoEncontradoException exception) {
                System.err.println(exception.getMessage());
            }
        } else {
            usuarioLogado = null;
            System.out.println("Log-out feito com sucesso!");
        }
        System.out.println("============================================================");
    }

    public static void imprimirMenuCadastro() {

        Participante participante;

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
                System.out.println(exception.getMessage());
            }
        }

        System.out.print("Digite sua titulação acadêmica: ");
        String titulacaoAcademica = scanner.next();
        System.out.print("Digite o nome da instituição na qual está vinculada(o): ");
        String instituicaoDeVinculo = scanner.next();

        participante = new Participante(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
        congresso.addParticipante(participante);
        usuarioLogado = participante;

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


    }


    public static void main(String[] args) {
        GeneralChair admin = new GeneralChair(
                "admin",
                "admin",
                "admin",
                LocalDate.now(),
                "Administrador do sistema",
                "CBPOO"
        );
        congresso.addParticipante(admin);

        int opcao;
        do {
            Main.imprimirMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> Main.imprimirMenuLogin();
                case 2 -> {
                    //TODO
                    Main.imprimirMenuCadastro();
                }
                default -> {
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
