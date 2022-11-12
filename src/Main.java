import exceptions.*;
import models.Artigo;
import models.Congresso;
import models.Participante;
import models.Pessoa;
import models.especialistas.Autor;
import models.especialistas.Revisor;
import models.organizadores.RootAdmin;
import models.organizadores.GeneralChair;
import models.organizadores.ProgramChair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static services.ConversorData.converterDataParaLocalDate;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Congresso congresso = Congresso.getInstance();
    private static Pessoa usuarioLogado = null;

    public static void imprimirCabecalhoMenu() {
        if (usuarioLogado instanceof ProgramChair) {
            System.out.println("====================== Program Chair =======================");
        } else if (usuarioLogado instanceof GeneralChair) {
            System.out.println("====================== General Chair =======================");
        } else if (usuarioLogado instanceof Revisor) {
            System.out.println("======================= Revisor(a) =========================");
        } else if (usuarioLogado instanceof Autor) {
            System.out.println("======================== Autor(a) ==========================");
        }
        if (usuarioLogado != null) {
            System.out.println("Boas-vindas, " + usuarioLogado.getNome());
            System.out.println("============================================================");
        }
    }

    public static void executarMenuListarParticipantes() {
        System.out.println("================== Lista de participantes ==================");
        if (congresso.getParticipantes().size() == 1) { // Sempre haverá 1 participante, que é o RootAdmin
            System.out.println("Não há participantes cadastrados!");
        } else congresso.listarParticipantesEmOrdemAlfabetica();
        System.out.println("============================================================");

    }

    public static void imprimirMenuGeneralChair() {
        System.out.println("8. Validar inscrição");
        System.out.println("9. Emitir certificado");
        System.out.println("10. Cadastrar participante");
    }

    public static void imprimirMenuProgramChair() {
        System.out.println("11. Avaliar artigo");
        System.out.println("12. Rejeitar artigo");
    }

    public static void executarMenuPrincipal() {
        int opcao;
        do {
            try {
                imprimirCabecalhoMenu();

                if (usuarioLogado == null) {
                    System.out.println("1. Fazer login");
                } else {
                    System.out.println("1. Fazer log-out");
                }

                if (usuarioLogado == null || usuarioLogado instanceof GeneralChair) {
                    System.out.println("2. Realizar inscrição");
                }
                System.out.println("3. Submeter artigo");
                System.out.println("4. Listar artigos aceitos em ordem alfabética");
                System.out.println("5. Listar artigos negados em ordem alfabética");
                System.out.println("6. Ver dados de um artigo");
                System.out.println("7. Listar participantes em ordem alfabética");

                if (usuarioLogado != null) {
                    if (usuarioLogado instanceof GeneralChair) {
                        imprimirMenuGeneralChair();
                    } else if (usuarioLogado instanceof ProgramChair) {
                        imprimirMenuProgramChair();
                    } else if (usuarioLogado instanceof Revisor) {
                        System.out.println("13. Enviar avaliação de artigo");
                    }

                    System.out.println("14. Ver avaliações de um artigo");
                }

                System.out.println("99. Encerrar o programa");
                System.out.println("============================================================");

                do {
                    try {
                        System.out.print("Digite uma opção: ");
                        opcao = Integer.parseInt(scanner.next());
                        break;
                    } catch (NumberFormatException err) {
                        System.out.println("Entrada inválida. Digite novamente.");
                    }
                } while (true);
                switch (opcao) {
                    case 1 -> {
                        if (usuarioLogado == null) {
                            Main.executarMenuLogin();
                        } else {
                            usuarioLogado = null;
                            System.out.println("Log-out realizado com sucesso!");
                        }
                    }
                    case 2, 10 -> {
                        Main.imprimirMenuCadastro();
                    }
                    case 3 -> {
                        //TODO: Submissão de artigo
                        imprimirMenuCadastroArtigo();
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
                        Main.executarMenuListarParticipantes();
                    }
                    case 8 -> {
                        //TODO: validar inscrição
                        try {
                            System.out.print("CPF da(o) participante que deseja validar inscrição: ");
                            String cpf = scanner.next().trim();
                            Participante participante = congresso.buscarParticipantePorCpf(cpf);
                            System.out.print("Deseja S ou N: ");
                            String validadeInscricao = scanner.next().trim().toUpperCase();

                            if (usuarioLogado instanceof GeneralChair) {
                                if (validadeInscricao.startsWith("S")) {
                                    ((GeneralChair) usuarioLogado).validarInscricaoParticipante(participante);
                                    System.out.println("Inscrição validada com sucesso!");
                                } else {
                                    ((GeneralChair) usuarioLogado).invalidarInscricaoParticipante(participante);
                                    System.out.println("Inscrição invalidada com sucesso!");
                                }
                            }

                        } catch (ParticipanteNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    //TODO: Cadastrar organizador
                    case 99 -> {
                        System.out.println("Programa encerrado!");
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

        } while (true);
    }

    public static void executarMenuLogin() {
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

    public static Participante imprimirMenuCadastro() {

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
                System.out.print("Informe a data de nascimento (dd/MM/aaaa): ");
                String dataNascimento = scanner.next();
                try {
                    dataNascimentoFormatada = converterDataParaLocalDate(dataNascimento);
                    break;
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }

            System.out.print("Informe a titulação acadêmica: ");
            String titulacaoAcademica = scanner.next();
            System.out.print("Informe o nome da instituição de vínculo: ");
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
                System.out.println("============================================================");
                System.out.println("Inscrição realizada com sucesso!");
                System.out.println("============================================================");
            } else {
                System.out.println("============================================================");
                System.out.println("Inscrição submetida para avaliação.");
                System.out.println("============================================================");
            }

            return participante;

        } catch (CpfJaCadastradoException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void imprimirMenuCadastroArtigo() {

        ArrayList<Participante> autores = new ArrayList<>();
        Artigo artigo;
        String cpf;

        Participante autorPrincipal = null;

        try {
            System.out.print("Digite o CPF do autor principal: ");
            cpf = scanner.next().trim();
            autorPrincipal = congresso.buscarParticipantePorCpf(cpf);

            System.out.println("==");
            if (autorPrincipal.isInscricaoValida()) {
                System.out.println("Vamos cadastrar o artigo");
                autores.add(autorPrincipal);

                System.out.println("Existem outros autores(as)?");

                if (autores.size() < 2) {
                    Participante coAutor = imprimirMenuCadastro();
                    autores.add(coAutor);
                } else {
                    System.out.println("Acabou");
                }

            } else if (autorPrincipal.isValidacaoPendente()) {
                System.out.println(new InscricaoPendenteException().getMessage());

            } else {
                System.out.println(new InscricaoRecusadaException().getMessage());
            }
            System.out.println("==");

        } catch (ParticipanteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        if (autorPrincipal == null) {
            System.out.println("Realizando cadastro..."); //TODO: A ideia é que pergunte se deseja cadastrar, etc.
            autorPrincipal = imprimirMenuCadastro();
        }


    }

    public static void main(String[] args) {
        RootAdmin rootAdmin = new RootAdmin(
                "admin",
                "rootAdmin",
                "admin",
                LocalDate.now(),
                "Administrador do sistema",
                "CBPOO"
        );
        congresso.addParticipante(rootAdmin);

        executarMenuPrincipal();
    }

}
