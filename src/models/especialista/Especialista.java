package models.especialista;

import models.Participante;

import java.time.LocalDate;

public class Especialista extends Participante {

    protected String especialidade;

    public Especialista(String cpf, String nome, String senha, LocalDate dataNascimento,
                        String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.especialidade = especialidade;
    }
}
