package selton.dev.desafio_relogios.dto;

import java.util.UUID;

public record RelogioDTO(
    UUID id,
    String marca,
    String modelo,
    String referencia,
    String tipoMovimento,
    String materialCaixa,
    String tipoVidro,
    Integer resistenciaAguaM,
    Integer diametroMm,
    Integer lugtoLugMm,
    Integer espessuraMm,
    Integer larguraLugMm,
    Long precoEmCentavos,
    String urlImagem,
    String etiquetaResistenciaAgua,
    Integer pontuacaoColecionador
) {
    
}
