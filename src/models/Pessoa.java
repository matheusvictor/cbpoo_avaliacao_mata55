package models;

import java.time.LocalDate;

public class Pessoa {

    protected String cpf;
    protected String nome;
    protected String senha;
    protected LocalDate dataNascimento;
    protected String titulacaoAcademica;
    protected String instituicaoDeVinculo;

    public Pessoa(String cpf, String nome, String senha, LocalDate dataNascimento,
                  String titulacaoAcademica, String instituicaoDeVinculo) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.titulacaoAcademica = titulacaoAcademica;
        this.instituicaoDeVinculo = instituicaoDeVinculo;
    }

}
