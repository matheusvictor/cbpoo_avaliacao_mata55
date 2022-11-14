package exceptions;

import static models.Artigo.QTD_PALAVRAS_CHAVE;

public class NumeroMaximoPalavrasChaveException extends Exception {

    public NumeroMaximoPalavrasChaveException() {
        super("Um artigo não pode conter mais de " + QTD_PALAVRAS_CHAVE + " palavras-chave!");
    }

}
