package models.especialistas;

import models.Artigo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Revisor extends Especialista {
    private ArrayList<Artigo> artigosRevisados = new ArrayList<>();

    public Revisor(String cpf, String nome, LocalDate dataNascimento,
                        String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
    }

    public void avaliarArtigo(Artigo artigo, String avaliacao) {
        artigo.vincularRevisor(this);
        this.artigosRevisados.add(artigo);
        artigo.setAguardandoAvaliacao(false);
        artigo.adicionarAvaliacaoDoRevisor(avaliacao);
    }

}
