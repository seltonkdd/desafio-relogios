package selton.dev.desafio_relogios.exception.custom;

public class RelogioNaoEncontradoException extends RuntimeException {
    public RelogioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
