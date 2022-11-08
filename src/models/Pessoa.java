package models;

import java.time.LocalDate;

public abstract class Pessoa {

    protected String cpf;
    protected String nome;
    protected String senha;
    protected LocalDate dataNascimento;
    protected String titulacaoAcademica;
    protected String instituicaoDeVinculo;

    public Pessoa(String cpf, String nome, LocalDate dataNascimento,
                  String titulacaoAcademica, String instituicaoDeVinculo) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.titulacaoAcademica = titulacaoAcademica;
        this.instituicaoDeVinculo = instituicaoDeVinculo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setTitulacaoAcademica(String titulacaoAcademica) {
        this.titulacaoAcademica = titulacaoAcademica;
    }

    public String getTitulacaoAcademica() {
        return titulacaoAcademica;
    }

    public void setInstituicaoDeVinculo(String instituicaoDeVinculo) {
        this.instituicaoDeVinculo = instituicaoDeVinculo;
    }

    public String getInstituicaoDeVinculo() {
        return instituicaoDeVinculo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", titulacaoAcademica='" + titulacaoAcademica + '\'' +
                ", instituicaoDeVinculo='" + instituicaoDeVinculo + '\'' +
                '}';
    }

}
