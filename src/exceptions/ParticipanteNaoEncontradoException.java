package exceptions;

public class ParticipanteNaoEncontradoException extends Exception {

    public ParticipanteNaoEncontradoException() {
        super("Participante não encontrado!");
    }

}
