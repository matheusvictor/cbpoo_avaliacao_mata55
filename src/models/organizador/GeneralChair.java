package models.organizador;

import models.Participante;

import java.util.HashSet;

public class GeneralChair {

    protected HashSet<Participante> participantes;

    public void validarInscricao(Participante p) {
        p.setInscricaoValida(true);
        p.setValidacaoPendente(false);
    }

    public void invalidarInscricao(Participante p) {
        p.setValidacaoPendente(false);
    }

    public void emitirCertificado() {

    }
}
