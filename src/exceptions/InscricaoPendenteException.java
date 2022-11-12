package exceptions;

public class InscricaoPendenteException extends Exception {

    public InscricaoPendenteException() {
        super("Sua inscrição ainda está sendo analisada");
    }

}
