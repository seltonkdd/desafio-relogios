package selton.dev.desafio_relogios.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record AtualizarRelogioRequest(
    @Size(max = 80) String marca,
    @Size(max = 80) String modelo,
    @Size(max = 80) String referencia,
    @Size(max = 20) String tipoMovimento,
    @Size(max = 20) String materialCaixa,
    @Size(max = 20) String tipoVidro,
    @Min(0) Integer resistenciaAguaM,
    @Min(5) Integer diametroMm,
    @Min(10) Integer lugtoLugMm,
    @Min(5) Integer espessuraMm,
    @Min(10) Integer larguraLugMm,
    @Min(1) Long precoEmCentavos,
    @Size(max = 600) String urlImagem
) {
    
}
