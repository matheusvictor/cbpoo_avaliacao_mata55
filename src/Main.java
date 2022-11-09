import exceptions.ParticipanteNaoEncontradoException;
import models.Congresso;
import models.Participante;
import models.Pessoa;
import models.organizadores.GeneralChair;
import models.organizadores.ProgramChair;

import java.time.LocalDate;
import java.util.Scanner;

import static services.ConversorData.converterDataParaLocalDate;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Pessoa usuarioLogado = null;
    private static Congresso congresso = Congresso.getInstance();


    public static void imprimirMenu() {
        System.out.println("============================================================");
        Main.imprimirCabecalhoMenu();
        System.out.println("============================================================");
        if (usuarioLogado == null) {
            System.out.println("1. Fazer login");
            System.out.println("2. Inscrever-me");
        } else {
            System.out.println("1. Fazer log-out");
            if (usuarioLogado instanceof GeneralChair) {
                System.out.println("2. Cadastrar organizadores");
                System.out.println("3. Valiar inscrição");
                System.out.println("5. Emitir certificado");
            } else if (usuarioLogado instanceof ProgramChair) {
                System.out.println("9. Aceitar artigo (exclusivo para program chair)");
                System.out.println("10. Rejeitar artigo (exclusivo para program chair)");
            }
            System.out.println("6. Submeter artigo");
            System.out.println("7. Enviar avaliação de artigo (exclusivo para revisores)");
            System.out.println("8. Ver avaliações de um artigo");

            System.out.println("11. Listar artigos aceitos em ordem alfabética");
            System.out.println("12. Listar artigos negados em ordem alfabética");
            System.out.println("13. Ver dados de um artigo");
            System.out.println("14. Listar participantes em ordem alfabética");
        }

        System.out.println("15. Encerrar o programa");
        System.out.println("Ou aperte qualquer outra tecla para voltar ao menu principal");
        System.out.println("============================================================");

        System.out.print("Digite uma opção: ");
    }

    public static void imprimirCabecalhoMenu() {
        if (usuarioLogado == null) {
            System.out.println("================= Visitante ====================");
        }
        else if (usuarioLogado instanceof GeneralChair) {
            System.out.println("================= General Chair ====================");
            System.out.println("Bem-vindo " + usuarioLogado.getNome());
        }
    }

    public static void imprimirMenuLogin() {
        Main.clearScreen();

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
    public static void imprimirMenuCadastro(){

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

        System.out.print("Digite sua titulação acadêmica: ");
        String titulacaoAcademica = scanner.next();
        System.out.print("Digite o nome da instituição na qual está vinculada(o): ");
        String instituicaoDeVinculo = scanner.next();

        Participante participante;
        if (usuarioLogado instanceof GeneralChair) {
            String categoria;
            if (categoria == "GC") {
                participante = new GeneralChair(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
            } else {
                participante = new ProgramChair(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
            }
        } else {
            participante = new Participante(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
        }

        congresso.addParticipante(participante);
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
                case 1:
                    Main.imprimirMenuLogin();
                    break;
                case 15:
                    System.exit(0);
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

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
