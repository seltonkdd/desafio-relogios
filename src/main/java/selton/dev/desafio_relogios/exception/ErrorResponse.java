package selton.dev.desafio_relogios.exception;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
    Instant timestamp,
    Integer status,
    String erro,
    String mensagem,
    String caminho,
    List<ErroCampo> errosDeCampo
) {
    public record ErroCampo(String campo, String mensagem) {}
}
