package selton.dev.desafio_relogios.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarRelogioRequest(
    @NotBlank @Size(max = 80) String marca,
    @NotBlank @Size(max = 80) String modelo,
    @NotBlank @Size(max = 80) String referencia,
    @NotBlank @Size(max = 20) String tipoMovimento,
    @NotBlank @Size(max = 20) String materialCaixa,
    @NotBlank @Size(max = 20) String tipoVidro,
    @Min(0) Integer resistenciaAguaM,
    @Min(5) Integer diametroMm,
    @Min(10) Integer lugtoLugMm,
    @Min(5) Integer espessuraMm,
    @Min(10) Integer larguraLugMm,
    @Min(1) Long precoEmCentavos,
    @NotBlank @Size(max = 600) String urlImagem
) {
    
}
