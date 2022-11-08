package models.organizadores;

import models.Artigo;
import models.especialistas.Especialista;

import java.time.LocalDate;
import java.util.List;

public class ProgramChair extends Organizador {

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

}
