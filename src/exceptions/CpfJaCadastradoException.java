package exceptions;

public class CpfJaCadastradoException extends Exception {

    public CpfJaCadastradoException() {
        super("CPF já cadastrado!");
    }

}
