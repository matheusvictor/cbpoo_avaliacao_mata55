package models;

import java.time.LocalDate;

public class Participante extends Pessoa {

    protected String senha;
    protected boolean inscricaoValida;
    protected boolean validacaoPendente;

    public Participante(String cpf, String nome, String senha,
                        LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.senha = senha;
        this.inscricaoValida = false;
        this.validacaoPendente = true;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isInscricaoValida() {
        return inscricaoValida;
    }

    public boolean isValidacaoPendente() {
        return validacaoPendente;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "inscricaoValida=" + inscricaoValida +
                ", validacaoPendente=" + validacaoPendente +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", titulacaoAcademica='" + titulacaoAcademica + '\'' +
                ", instituicaoDeVinculo='" + instituicaoDeVinculo + '\'' +
                '}';
    }

}
