package models.especialista;

import models.Artigo;

import java.time.LocalDate;
import java.util.List;

public class AutorDeArtigo extends Especialista {

    public AutorDeArtigo(String cpf, String nome, String senha, LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo, especialidade);
    }

    public void submterArtigo(Artigo artigo) {
        // TODO
    }

    public Artigo escreverArtigo(String titulo, String resumo, List<String> palavrasChave) {
        return null;
    }

}
