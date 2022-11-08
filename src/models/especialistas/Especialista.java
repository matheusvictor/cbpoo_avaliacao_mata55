package models.especialistas;

import models.Artigo;
import models.Pessoa;
import interfaces.VisualizarAvaliacoesArtigo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Especialista extends Pessoa implements VisualizarAvaliacoesArtigo {

    private String especialidade;

    public Especialista(String cpf, String nome, LocalDate dataNascimento,
                        String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public void listarAvaliacoesArtigos(ArrayList<Artigo> artigos) {
        for (Artigo artigo : artigos) {
            System.out.println(artigo.getAvaliacoesRevisores());
        }
    }

}
