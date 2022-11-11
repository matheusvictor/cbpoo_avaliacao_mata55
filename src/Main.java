import exceptions.ArtigoNaoEncontradoException;
import exceptions.CpfJaCadastradoException;
import models.Artigo;
import models.Pessoa;
import models.Congresso;
import models.Participante;
import models.especialistas.Autor;
import models.especialistas.Revisor;
import models.organizadores.GeneralChair;
import models.organizadores.ProgramChair;

import exceptions.ParticipanteNaoEncontradoException;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

import static services.ConversorData.converterDataParaLocalDate;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Congresso congresso = Congresso.getInstance();
    private static Pessoa usuarioLogado = null;

    public static void imprimirCabecalhoMenu() {
        System.out.println("============================================================");
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
            System.out.println("============================================================");
        }
    }

    public static void imprimirMenu() {
        Main.imprimirCabecalhoMenu();
        if (usuarioLogado == null) {
            System.out.println("1. Fazer login");
            System.out.println("2. Realizar inscrição");
            System.out.println("3. Submeter artigo");
            System.out.println("4. Listar artigos aceitos em ordem alfabética");
            System.out.println("5. Listar artigos negados em ordem alfabética");
            System.out.println("6. Ver dados de um artigo");
            System.out.println("7. Listar participantes em ordem alfabética");
        } else {

            System.out.println("0. Fazer log-out");

            if (usuarioLogado instanceof GeneralChair) {
                System.out.println("8. Validar inscrição");
                System.out.println("9. Emitir certificado");
                System.out.println("10. Cadastrar participante");
            } else if (usuarioLogado instanceof ProgramChair) {
                System.out.println("11. Avaliar artigo");
                System.out.println("12. Rejeitar artigo");
            } else if (usuarioLogado instanceof Revisor) {
                System.out.println("13. Enviar avaliação de artigo");
            }

            System.out.println("14. Ver avaliações de um artigo");
        }

        System.out.println("99. Encerrar o programa");
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
            } catch (Exception exception) {
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
        String cpf;

        try {
            System.out.print("Insira o CPF: ");
            cpf = scanner.next().trim();
            congresso.cpfJaCadastrado(cpf);

            System.out.print("Insira o nome: ");
            String nome = scanner.next();
            System.out.print("Insira uma senha: ");
            String senha = scanner.next().trim();

            LocalDate dataNascimentoFormatada;
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

            if (usuarioLogado instanceof GeneralChair) {
                String categoria;
                System.out.println("Qual a categoria do Participante?");
                System.out.println("GC - General Chair");
                System.out.println("PC - Program Chair");
                System.out.print("Digite a sigla da categoria desejada ou qualquer outra tecla para inscrever um participante comum: ");
                categoria = scanner.next().toUpperCase().trim();

                if (categoria.equals("GC")) {
                    participante = new GeneralChair(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
                } else if (categoria.equals("PC")) {
                    participante = new ProgramChair(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
                } else {
                    participante = new Participante(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
                }

                // Caso um General Chair esteja cadastrando um participante
                // seja ele um GC, PC ou qualquer outro, este já deve ter a inscrição válida
                participante.setInscricaoValida(true);
                participante.setValidacaoPendente(false);
            } else {
                participante = new Participante(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
            }

            congresso.addParticipante(participante);

            if (participante.isInscricaoValida()) {
                System.out.println("Inscrição realizada com sucesso!");
            } else {
                System.out.println("Inscrição submetida para avaliação.");
            }

        } catch (CpfJaCadastradoException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) throws CpfJaCadastradoException {

        GeneralChair admin = new GeneralChair(
                "admin",
                "admin",
                "admin",
                LocalDate.now(),
                "Administrador do sistema",
                "CBPOO");
        congresso.addParticipante(admin);

        int opcao;
        do {
            Main.imprimirMenu();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> Main.imprimirMenuLogin();
                case 2 -> {
                    //FIXME
                    Main.imprimirMenuCadastro();
                }
                case 3 -> {
                    System.out.println("Submissão de artigo (WIP)");
                    //TODO: Submissão de artigo
                }
                case 4 -> {
                    List<Artigo> artigosAceitos = congresso.getArtigosNegadosEmOrdemAlfabetica();
                    if (artigosAceitos.isEmpty()) {
                        System.out.println("============================================================");
                        System.out.println("Não há artigos aceitos para serem listados!");
                    } else {
                        for (Artigo artigo : artigosAceitos) {
                            System.out.println(artigo);
                        }
                    }
                }
                case 5 -> {
                    List<Artigo> artigosNegados = congresso.getArtigosNegadosEmOrdemAlfabetica();
                    if (artigosNegados.isEmpty()) {
                        System.out.println("============================================================");
                        System.out.println("Não há artigos negados para serem listados!");
                    } else {
                        for (Artigo artigo : artigosNegados) {
                            System.out.println(artigo);
                        }
                    }
                }
                case 6 -> {
                    System.out.print("Digite o ID do artigo que procura: ");
                    int id = scanner.nextInt();
                    try {
                        Artigo artigoEncontrado = congresso.buscarArtigoPorId(id);
                        System.out.println(artigoEncontrado);
                    } catch (ArtigoNaoEncontradoException exception) {
                        System.err.println(exception.getMessage());
                    }
                }
                case 7 -> {
                    System.out.println("================== Lista de participantes ==================");
                    congresso.listarParticipantesEmOrdemAlfabetica();
                }
                case 10 -> {
                    //TODO: Cadastrar organizador
                }
                default -> {
                    System.out.println("Programa encerrado!");
                    System.exit(0);
                }
            }
        } while (true);

    }

}
