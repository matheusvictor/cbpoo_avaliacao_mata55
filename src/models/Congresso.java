package models;

import exceptions.ArtigoNaoEncontradoException;
import exceptions.ParticipanteNaoEncontradoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Congresso {

    private static Congresso congresso;
    private ArrayList<Participante> participantes;
    private ArrayList<Artigo> artigos;

    public static Congresso getInstance() {
        if (congresso == null) {
            congresso = new Congresso();
        }
        return congresso;
    }

    private Congresso() {
        this.participantes = new ArrayList<>();
        this.artigos = new ArrayList<>();
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

    public Participante fazerLogin(String cpf, String senha) throws Exception {
        Participante participante = this.participantes.stream()
                .filter(p -> p.validarLogin(cpf, senha))
                .findFirst()
                .orElse(null);

        if (participante == null) {
            throw new ParticipanteNaoEncontradoException();
        }

        if (participante.isValidacaoPendente()) {
            throw new Exception("Sua inscrição ainda está sendo analisada");
        }

        if (!participante.isInscricaoValida()) {
            throw new Exception("Sua inscrição foi recusada!");
        }

        return participante;
    }

    public void addParticipante(Participante participante) {
        this.participantes.add(participante);
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

    public Artigo receberSubmissaoArtigo(Artigo artigo) {
        return null;
    }

    public void listarParticipantesEmOrdemAlfabetica() {
        ordenarParticipantesEmOrdemAlfabetica(this.participantes);
        for (Participante p : this.participantes) {
            System.out.println("============================================================");
            System.out.println(p);
        }
    }

    public List<Artigo> getArtigosNegadosEmOrdemAlfabetica() {
        this.ordenarArtigosEmOrdemAlfabetica(this.artigos);
        return this.artigos.stream().filter(artigo -> artigo.aprovado).toList();
    }

    public List<Artigo> getArtigosAceitosEmOrdemAlfabetica() {
        this.ordenarArtigosEmOrdemAlfabetica(this.artigos);
        return this.artigos.stream().filter(artigo -> artigo.aprovado).toList();
    }

    public Artigo buscarArtigoPorId(int id) throws ArtigoNaoEncontradoException {
        Artigo artigo = this.artigos.stream().filter(a -> a.getIdentificador() == id).findFirst().orElse(null);

        if (artigo == null) {
            throw new ArtigoNaoEncontradoException();
        }

        return artigo;
    }

    public void verDadosDeArtigo(int id) {
        if (this.artigos != null)
            for (Artigo artigo : this.artigos) {
                if (artigo.getIdentificador() == id) {
                    System.out.println(artigo);
                }
            }
    }

    private List<Artigo> ordenarArtigosEmOrdemAlfabetica(List<Artigo> artigos) {
        Collections.sort(artigos);
        return artigos;
    }

    private List<Participante> ordenarParticipantesEmOrdemAlfabetica(List<Participante> participantes) {
        Collections.sort(participantes);
        return participantes;
    }

}
