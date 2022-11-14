package exceptions;

import static models.Artigo.MAX_AUTORES;

public class NumeroMaximoAutoresException extends Exception {

    public NumeroMaximoAutoresException() {
        super("Um artigo não pode conter mais de " + MAX_AUTORES + " autores!");
    }

}
