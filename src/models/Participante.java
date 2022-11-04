package models;

import java.time.LocalDate;

public class Participante extends Pessoa {

    protected boolean inscricaoValida;
    protected boolean validacaoPendente;

    public Participante(String cpf, String nome, String senha, LocalDate dataNascimento,
                        String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.inscricaoValida = false;
        this.validacaoPendente = true;
    }



}
