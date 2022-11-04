package models.participante.especialista;

import models.participante.Participante;

import java.time.LocalDate;

public abstract class Especialista extends Participante {

    protected String especialidade;

    public Especialista(String cpf, String nome, String senha, LocalDate dataNascimento,
                        String titulacaoAcademica, String instituicaoDeVinculo, String especialidade) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
