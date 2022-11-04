package models.participante.especialista;

import models.Artigo;

import java.util.List;
import java.time.LocalDate;

public class Autor extends Especialista {

    public Autor(String cpf, String nome, String senha, LocalDate dataNascimento,
                 String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo, especialidade);
    }

    public Artigo escreverArtigo(String titulo, String resumo, List<String> palavrasChave) {
        return null;
    }

}
