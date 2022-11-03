package models;

import java.time.LocalDate;

public class Participante {

    protected String cpf;
    protected String nome;
    protected String senha;
    protected LocalDate dataNascimento;
    protected String titulacaoAcademica;
    protected String instituicaoDeVinculo;
    protected boolean validacaoPendente;
    protected boolean inscricaoValida;

    public Participante(String cpf, String nome, String senha,
                        LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.titulacaoAcademica = titulacaoAcademica;
        this.instituicaoDeVinculo = instituicaoDeVinculo;
        this.validacaoPendente = true;
        this.inscricaoValida = false;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getTitulacaoAcademica() {
        return titulacaoAcademica;
    }

    public String getInstituicaoDeVinculo() {
        return instituicaoDeVinculo;
    }

    public void setValidacaoPendente(boolean validacaoPendente) {
        this.validacaoPendente = validacaoPendente;
    }

    public boolean isValidacaoPendente() {
        return validacaoPendente;
    }

    public void setInscricaoValida(boolean inscricaoValida) {
        this.inscricaoValida = inscricaoValida;
    }

    public boolean isInscricaoValida() {
        return inscricaoValida;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", titulacaoAcademica='" + titulacaoAcademica + '\'' +
                ", instituicaoDeVinculo='" + instituicaoDeVinculo + '\'' +
                ", validacaoPendente=" + validacaoPendente +
                ", inscricaoValida=" + inscricaoValida +
                '}';
    }

}
