package models;

import java.time.LocalDate;

public abstract class Pessoa implements Comparable<Pessoa> {

    protected String cpf;
    protected String nome;
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

    @Override
    public int compareTo(Pessoa o) {
        return this.getNome().compareTo(o.getNome());
    }

}
