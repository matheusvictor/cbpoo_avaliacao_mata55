package models.organizadores;

import models.Participante;

import java.time.LocalDate;

public class GeneralChair extends Organizador {

    public GeneralChair(String cpf, String nome, String senha,
                        LocalDate dataNascimento, String titulacaoAcademica, String instituicaoDeVinculo) {
        super(cpf, nome, senha, dataNascimento, titulacaoAcademica, instituicaoDeVinculo);
    }

    public void validarInscricaoParticipante(Participante participante) {
        participante.setInscricaoValida(true);
        participante.setValidacaoPendente(false);
    }

    public void invalidarInscricaoParticipante(Participante participante) {
        participante.setInscricaoValida(false);
        participante.setValidacaoPendente(false);
    }

    public void emitirCertificadoParaParticipante(Participante participante) {
        if (!participante.isCertificado()) participante.setCertificado(true);
    }

}
