package models;

import java.time.LocalDate;

public class Participante extends Pessoa {

    protected String senha;
    protected boolean certificado;
    protected boolean inscricaoValida;
    protected boolean validacaoPendente;

    public Participante(String cpf, String nome, String senha,
                        LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.senha = senha.trim();
        this.certificado = false;
        this.inscricaoValida = false;
        this.validacaoPendente = true;
    }

    public boolean isCertificado() {
        return certificado;
    }

    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    public boolean isInscricaoValida() {
        return inscricaoValida;
    }

    public void setInscricaoValida(boolean inscricaoValida) {
        this.inscricaoValida = inscricaoValida;
    }

    public boolean isValidacaoPendente() {
        return validacaoPendente;
    }

    public void setValidacaoPendente(boolean validacaoPendente) {
        this.validacaoPendente = validacaoPendente;
    }

    public boolean validarLogin(String cpf, String senha) {
        return this.getCpf().equals(cpf) && this.senha.equals(senha);
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
