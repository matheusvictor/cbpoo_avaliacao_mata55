package models;

import java.time.LocalDate;

public class Participante extends Pessoa {

    protected String senha;
    protected boolean certificado;
    protected boolean inscricaoValida;
    protected boolean validacaoPendente;

    public Participante() {
        super();
    }

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

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public String obterDetalhes() {
        String descricaoValidadeInscricao = inscricaoValida ? "Sim" : "Não";
        String descricaoPendenciaInscricao = isValidacaoPendente() ? "Sim" : "Não";
        String certificadoEmitido = certificado ? "Sim" : "Não";
        return "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "Data de nascimento: " + dataNascimento + "\n" +
                "Titulação acadêmica: " + titulacaoAcademica + "\n" +
                "Instituição de vínculo: " + instituicaoDeVinculo + "\n" +
                "Inscrição válida: " + descricaoValidadeInscricao + "\n" +
                "Validação pendente: " + descricaoPendenciaInscricao + "\n" +
                "Certificado: " + certificadoEmitido;
    }

    @Override
    public String toString() {
        return nome + " - " + titulacaoAcademica + " - " + instituicaoDeVinculo;
    }

}
