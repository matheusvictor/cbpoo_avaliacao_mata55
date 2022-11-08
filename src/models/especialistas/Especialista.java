package models.especialistas;

import models.Pessoa;

import java.time.LocalDate;

public class Especialista extends Pessoa {

    private String especialidade;

    public Especialista(String cpf, String nome, LocalDate dataNascimento,
                        String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
