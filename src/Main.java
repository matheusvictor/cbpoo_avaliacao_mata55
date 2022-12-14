import exceptions.*;
import models.Artigo;
import models.Congresso;
import models.Participante;
import models.Pessoa;
import models.especialistas.Autor;
import models.especialistas.Revisor;
import models.organizadores.GeneralChair;
import models.organizadores.ProgramChair;
import models.organizadores.RootAdmin;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static models.Artigo.MAX_AUTORES;
import static models.Artigo.QTD_PALAVRAS_CHAVE;
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
                if (!(usuarioLogado instanceof GeneralChair)) {
                    System.out.println("3. Submeter artigo");
                }
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
                    case 2 -> {
                        Main.imprimirMenuCadastro();
                    }
                    case 3 -> {
                        if (usuarioLogado instanceof RootAdmin) {
                            System.out.println("Opção inválida!");
                        } else {
                            imprimirMenuCadastroArtigo();
                        }
                    }
                    case 4 -> {
                        Main.executarMenuListarArtigos(true);
                    }
                    case 5 -> {
                        Main.executarMenuListarArtigos(false);
                    }
                    case 6 -> {
                        Main.executarMenuDetalhesArtigoPorId();
                    }
                    case 7 -> {
                        Main.executarMenuListarParticipantes();
                    }
                    case 8 -> {
                        Main.executarMenuValidacaoParticipante();
                    }
                    case 9 -> {
                        Main.executarMenuCertificacaoParticipante();
                    }
                    case 99 -> {
                        System.out.println("Programa encerrado!");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Opção inválida!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

        } while (true);
    }

    private static void executarMenuDetalhesArtigoPorId() {
        System.out.print("Digite o ID do artigo que procura: ");
        int id = scanner.nextInt();

        System.out.println("============================================================");
        try {
            Artigo artigoEncontrado = congresso.buscarArtigoPorId(id);
            System.out.println(artigoEncontrado.obterDetalhes());
        } catch (ArtigoNaoEncontradoException exception) {
            System.err.println(exception.getMessage());
        }
        System.out.println("============================================================");
    }

    private static void executarMenuListarArtigos(boolean isAceito) {
        List<Artigo> artigos;

        if (isAceito) {
            artigos = congresso.getArtigosAceitosEmOrdemAlfabetica();
        } else {
            artigos = congresso.getArtigosNegadosEmOrdemAlfabetica();
        }

        System.out.println("============================================================");
        if (artigos.isEmpty()) {
            System.out.println("Não há artigos para serem listados!");
        } else {
            int indice = 1;
            for (Artigo artigo : artigos) {
                System.out.println(indice + ". " + artigo);
                indice++;
            }
        }
        System.out.println("============================================================");
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

        Artigo artigo = new Artigo();
        String cpf;

        Participante autorPrincipal;

        if (usuarioLogado != null) {
            autorPrincipal = (Participante) usuarioLogado;
        } else {
            try {
                System.out.print("Digite o CPF do(a) autor(a) principal: ");
                cpf = scanner.next();
                autorPrincipal = congresso.buscarParticipanteValidoPorCpf(cpf);

                if (autorPrincipal instanceof RootAdmin) {
                    throw new ParticipanteNaoEncontradoException();
                }

            } catch (ParticipanteNaoEncontradoException | InscricaoPendenteException | InscricaoRecusadaException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        try {
            artigo.addAutor(autorPrincipal);
        } catch (NumeroMaximoAutoresException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.print("Digite o título do artigo: ");
        artigo.setTitulo(scanner.next());

        System.out.print("Digite o resumo do artigo: ");
        artigo.setResumo(scanner.next());

        int contadorPalavrasChave = 1;
        do {
            try {
                System.out.print("Digite a " + contadorPalavrasChave + "ª palavra-chave: ");
                artigo.addPalavraChave(scanner.next());
            } catch (NumeroMaximoPalavrasChaveException exception) {
                System.out.println(exception.getMessage());
            }
            contadorPalavrasChave++;
        } while (contadorPalavrasChave <= QTD_PALAVRAS_CHAVE);

        System.out.print("Digite a qtd. de páginas do artigo: ");
        artigo.setQuantidadeDePaginas(scanner.nextInt()); //TODO: criar exceção para tipo de entrada

        do {
            System.out.print("Deseja cadastrar um(a) co-autor(a)? [S/N] ");
            String opcao = scanner.next().trim().toUpperCase();

            if (opcao.startsWith("N")) {
                break;
            } else {
                System.out.print("CPF do(a) co-autor(a): ");
                cpf = scanner.next();

                System.out.print("Nome do(a) co-autor(a): ");
                String nome = scanner.next();

                LocalDate dataNascimentoFormatada;
                while (true) {
                    System.out.print("Data de Nascimento (dd/mm/aaaa) do(a) co-autor(a): ");
                    String dataNascimentoCoAutor = scanner.next();
                    try {
                        dataNascimentoFormatada = converterDataParaLocalDate(dataNascimentoCoAutor);
                        break;
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                }

                System.out.print("Titulação acadêmica do(a) co-autor(a): ");
                String titulacaoAcademica = scanner.next();

                System.out.print("Instituição de vínculo do(a) co-autor(a): ");
                String instituicaoDeVinculo = scanner.next();

                Autor coAutor = new Autor(cpf, nome, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);

                try {
                    artigo.addAutor(coAutor);
                } catch (NumeroMaximoAutoresException e) {
                    System.out.println(e.getMessage());
                }
            }

        } while (artigo.getAutores().size() < MAX_AUTORES);

        // Converte o Participante em um autor
        try {
            congresso.addArtigo(artigo);
            congresso.removerParticipante(autorPrincipal);
            autorPrincipal = Autor.converterParticipante(autorPrincipal);
            ((Autor) autorPrincipal).addArtigoSubmetido(artigo);
            congresso.addParticipante(autorPrincipal);
        } catch (ParticipanteNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
        ((Autor) autorPrincipal).addArtigoSubmetido(artigo);
    }

    private static void executarMenuValidacaoParticipante() {
        try {
            System.out.print("CPF da(o) participante que deseja validar inscrição: ");
            String cpf = scanner.next().trim();
            Participante participante = congresso.buscarParticipantePorCpf(cpf);

            String validadeInscricao;

            do {
                System.out.print("Digite S para validar ou N para invalidar a inscrição: ");
                validadeInscricao = scanner.next().trim().toUpperCase();

                if (validadeInscricao.startsWith("S")) {
                    ((GeneralChair) usuarioLogado).validarInscricaoParticipante(participante);
                    System.out.println("============================================================");
                    System.out.println("A inscrição de " + participante.getNome() + " foi validada com sucesso!");
                    break;
                } else if (validadeInscricao.startsWith("N")) {
                    ((GeneralChair) usuarioLogado).invalidarInscricaoParticipante(participante);
                    System.out.println("============================================================");
                    System.out.println("A inscrição de " + participante.getNome() + " foi invalidada com sucesso!");
                    break;
                } else {
                    System.out.print("Opção inválida! ");
                }
            } while (true);


        } catch (ParticipanteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executarMenuCertificacaoParticipante() {
        try {
            System.out.print("CPF da(o) participante que deseja emitir o certificado: ");
            String cpf = scanner.next().trim();
            Participante participante = congresso.buscarParticipantePorCpf(cpf);

            if (participante.isInscricaoValida()) {
                ((GeneralChair) usuarioLogado).emitirCertificadoParaParticipante(participante);
                System.out.println("============================================================");
                System.out.println("Certificado de " + participante.getNome() + " emitido com sucesso!");
                System.out.println("============================================================");
                System.out.println(participante.obterDetalhes());
            } else if (!participante.isValidacaoPendente() && !participante.isInscricaoValida()) {
                System.out.println("Certificado não pôde ser emitido, pois o participante teve a inscrição recusada!");
            } else {
                System.out.println("Participante não possui uma inscrição válida!");
            }

        } catch (ParticipanteNaoEncontradoException e) {
            System.out.println(e.getMessage());
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
