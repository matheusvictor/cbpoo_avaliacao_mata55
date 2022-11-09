package models.organizadores;

import models.Artigo;
import models.especialistas.Especialista;
import interfaces.VisualizarAvaliacoesArtigo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramChair extends Organizador implements VisualizarAvaliacoesArtigo {

    private List<Especialista> especialistas;

    public ProgramChair(String cpf, String nome, String senha,
                        LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
    }

    public void aprovarArtigo(Artigo artigo) {
        artigo.setAprovado(true);
        artigo.setAguardandoAvaliacao(false);
    }

    public void reprovarArtigo(Artigo artigo) {
        artigo.setAprovado(false);
        artigo.setAguardandoAvaliacao(false);
    }

    @Override
    public void listarAvaliacoesArtigos(ArrayList<Artigo> artigos) {
        for (Artigo artigo : artigos) {
            System.out.println(artigo.getAvaliacoesRevisores());
        }
    }

}
