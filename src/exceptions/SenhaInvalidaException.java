package exceptions;

public class SenhaInvalidaException extends Exception {
    public SenhaInvalidaException() {
        super("Senha inválida!");
    }
}
