package models;

import exceptions.ParticipanteNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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


    public Participante fazerLogin(String cpf, String senha) throws ParticipanteNaoEncontradoException {
        Participante participante = this.participantes.stream()
                .filter(p -> p.validarLogin(cpf, senha))
                .findFirst()
                .orElse(null);

        if (participante == null) {
            throw new ParticipanteNaoEncontradoException();
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


    public void listarParticipantesEmOrdemAlfabetica() {
        ordenarParticipantesEmOrdemAlfabetica(this.participantes);
        for (Participante p : this.participantes) {
            System.out.println(p);
        }
    }

    public Artigo receberSubmissaoArtigo(Artigo artigo) {
        return null;
    }

    public List<Artigo> listarArtigosNegadosEmOrdemAlfabetica() {
        List<Artigo> artigosNegados = this.artigos.stream().filter(artigo -> !artigo.aprovado).toList();
        return this.ordenarArtigosEmOrdemAlfabetica(artigosNegados);
    }

    public List<Artigo> listarArtigosAceitosEmOrdemAlfabetica() {
        List<Artigo> artigosAceitos = this.artigos.stream().filter(artigo -> artigo.aprovado).toList();
        return this.ordenarArtigosEmOrdemAlfabetica(artigosAceitos);
    }

    public Artigo buscarArtigo(int id) {
        return this.artigos.stream().filter(a -> a.getIdentificador() == id).findFirst().orElse(null);
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
