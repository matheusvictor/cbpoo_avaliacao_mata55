package models;

import exceptions.*;
import models.organizadores.RootAdmin;

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

    public Participante fazerLogin(String cpf, String senha) throws Exception {
        Participante participante = buscarParticipanteValidoPorCpf(cpf);

        boolean senhaValida = participante.verificarSenha(senha);

        if (!senhaValida) {
            throw new SenhaInvalidaException();
        }

        return participante;
    }

    public void addParticipante(Participante participante) {
        this.participantes.add(participante);
    }

    public void addArtigo(Artigo artigo) {
        this.artigos.add(artigo);
    }

    public void cpfJaCadastrado(String cpf) throws CpfJaCadastradoException {
        if (!this.participantes.isEmpty()) {
            for (Participante p : this.participantes) {
                if (p.getCpf().equals(cpf)) {
                    throw new CpfJaCadastradoException();
                }
            }
        }
    }

    public boolean receberSubmissaoArtigo(Artigo artigo) throws Exception {
        if (artigo.getAutores().isEmpty()) {
            throw new Exception("O artigo precisa de pelo menos um(a) autor(a)!");
        }

        return true;
    }

    public Participante buscarParticipantePorCpf(String cpf) throws ParticipanteNaoEncontradoException {
        Participante participante = this.participantes.stream()
                .filter(p -> p.getCpf().equals(cpf)).findFirst().orElse(null);

        if (participante == null) {
            throw new ParticipanteNaoEncontradoException();
        }

        return participante;
    }

    public Participante buscarParticipanteValidoPorCpf(String cpf)
            throws ParticipanteNaoEncontradoException, InscricaoPendenteException, InscricaoRecusadaException {
        Participante participante = buscarParticipantePorCpf(cpf);

        if (participante.isValidacaoPendente()) {
            throw new InscricaoPendenteException();
        }

        if (!participante.isInscricaoValida()) {
            throw new InscricaoRecusadaException();
        }
        return participante;
    }

    public void listarParticipantesEmOrdemAlfabetica() {

        ordenarParticipantesEmOrdemAlfabetica(this.participantes);
        int indice = 1;
        for (Participante p : this.participantes) {
            if (p instanceof RootAdmin) {
                continue;
            }
            if (p.isInscricaoValida()) {
                System.out.println(indice + ". " + p);
                indice++;
            }
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

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public ArrayList<Artigo> getArtigos() {
        return artigos;
    }

    public void removerParticipante(Participante participante) throws ParticipanteNaoEncontradoException {
        participante = this.buscarParticipantePorCpf(participante.getCpf());
        this.participantes.remove(participante);
    }
}
