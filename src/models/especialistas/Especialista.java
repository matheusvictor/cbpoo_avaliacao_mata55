package models.especialistas;

import models.Artigo;
import models.Participante;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Especialista extends Participante {

    private String especialidade;

    public Especialista(String cpf, String nome, String senha, LocalDate dataNascimento,
                        String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void listarAvaliacoesArtigos(ArrayList<Artigo> artigos) {
        for (Artigo artigo : artigos) {
            System.out.println(artigo.getAvaliacoesRevisores());
        }
    }

}
