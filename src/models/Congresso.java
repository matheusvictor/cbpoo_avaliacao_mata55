package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static services.ConversorData.converterDataParaLocalDate;

public class Congresso {

    private static Congresso congresso;
    private ArrayList<Participante> participantes;
    private ArrayList<Artigo> artigosSubmetidos;
    private ArrayList<Artigo> artigosAceitos;
    private ArrayList<Artigo> artigosNegados;

    public static Congresso getInstance() {
        if (congresso == null) {
            congresso = new Congresso();
        }
        return congresso;
    }

    private Congresso() {
        this.participantes = new ArrayList<>();
        this.artigosSubmetidos = new ArrayList<>();
        this.artigosAceitos = new ArrayList<>();
        this.artigosNegados = new ArrayList<>();
    }

    public void imprimirMenu() {
        System.out.println("============================================================");
        System.out.println("1. Fazer login");
        System.out.println("2. Inscrever participante");
        System.out.println("3. Validar inscrição (exclusivo para general chair)");
        System.out.println("4. Invalidar inscrição (exclusivo para general chair)");
        System.out.println("5. Emitir certificado (exclusivo para general chair)");
        System.out.println("6. Submeter artigo");
        System.out.println("7. Enviar avaliação de artigo (exclusivo para revisores)");
        System.out.println("8. Ver avaliações de um artigo");
        System.out.println("9. Aceitar artigo (exclusivo para program chair)");
        System.out.println("10. Rejeitar artigo (exclusivo para program chair)");
        System.out.println("11. Listar artigos aceitos em ordem alfabética");
        System.out.println("12. Listar artigos negados em ordem alfabética");
        System.out.println("13. Ver dados de um artigo");
        System.out.println("14. Listar participantes em ordem alfabética");
        System.out.println("15. Encerrar o programa");
        System.out.println("============================================================");
    }

    public Participante fazerLogin(String cpf, String senha) {
        if (!this.participantes.isEmpty()) {
            for (Participante p : this.participantes) {
                if (p.getCpf().equals(cpf) && p.getSenha().equals(senha)) {
                    return p;
                }
            }
        }
        return null; // TODO: adicionar lançamento de Exception
    }

    public Participante inscreverParticipante(String cpf, String nome, String senha,
                                              String dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {

        LocalDate dataNascimentoFormatada = converterDataParaLocalDate(dataNascimento);
        Participante participante = new Participante(cpf, nome, senha, dataNascimentoFormatada, titulacaoAcademica, instituicaoDeVinculo);
        // LocalDate dataNascimentoFormatada = converterDataParaLocalDate("02/06/1998");
        // Participante participante = new Participante("123.456.789-00", "Lorem", "1234", dataNascimentoFormatada, "Bacharel", "UFBA");
        this.participantes.add(participante);
        return participante;
    }

    private boolean participanteEstaInscrito(String cpf) {
        if (!this.participantes.isEmpty()) {
            for (Participante p : this.participantes) {
                if (p.getCpf().equals(cpf)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void ordenarParticipantesEmOrdemAlfabetica() {
        Collections.sort(this.participantes);
    }

    public void listarParticipantesEmOrdemAlfabetica() {
        ordenarParticipantesEmOrdemAlfabetica();
        for (Participante p : this.participantes) {
            System.out.println(p);
        }
    }

    public Artigo receberSubmissaoArtigo(Artigo artigo) {
        return null;
    }

    private void ordenarArtigosEmOrdemAlfabetica(ArrayList<Artigo> artigos) {
        Collections.sort(artigos);
    }

    public void listarArtigosNegadosEmOrdemAlfabetica() {
        if (this.artigosNegados != null) {
            ordenarArtigosEmOrdemAlfabetica(this.artigosNegados);
            for (Artigo a : this.artigosNegados) {
                System.out.println(a);
            }
        }
    }

    public void listarArtigosAceitosEmOrdemAlfabetica() {
        if (this.artigosAceitos != null) {
            ordenarArtigosEmOrdemAlfabetica(this.artigosAceitos);
            for (Artigo a : this.artigosAceitos) {
                System.out.println(a);
            }
        }
    }

    public void verDadosDeArtigo(int id) {
        if (this.artigosSubmetidos != null)
            for (Artigo artigo : this.artigosSubmetidos) {
                if (artigo.getIdentificador() == id) {
                    System.out.println(artigo);
                }
            }
    }

}
