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
