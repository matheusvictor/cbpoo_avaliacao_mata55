package models.organizadores;

import models.Participante;

import java.time.LocalDate;

public class Organizador extends Participante {

    public Organizador(String cpf, String nome, String senha, LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
        setInscricaoValida(true);
        setValidacaoPendente(false);
    }

}
