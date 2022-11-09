package exceptions;

public class ParticipanteNaoEncontradoException extends Exception {

    public ParticipanteNaoEncontradoException() {
        super("Usuário nao encontrado");
    }

}
