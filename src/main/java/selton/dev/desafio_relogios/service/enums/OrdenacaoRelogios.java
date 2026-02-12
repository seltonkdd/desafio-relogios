package selton.dev.desafio_relogios.service.enums;

public enum OrdenacaoRelogios {
    MAIS_RECENTES,
    PRECO_CRESC,
    PRECO_DESC,
    DIAMETRO_CRESC,
    RESISTENCIA_DESC;

    public static OrdenacaoRelogios fromApi(String valor) {
        if (valor == null || valor.isBlank()) {
            return MAIS_RECENTES;
        }

        return switch (valor) {
            case "newest" -> MAIS_RECENTES;
            case "price_asc" -> PRECO_CRESC;
            case "price_desc" -> PRECO_DESC;
            case "diameter_asc" -> DIAMETRO_CRESC;
            case "wr_desc" -> RESISTENCIA_DESC;
            default -> throw new IllegalArgumentException("Ordenação inválida: " + valor);
        };
    } 

    public String toApi() {
        return switch (this) {
            case MAIS_RECENTES -> "newest";
            case PRECO_CRESC -> "price_asc";
            case PRECO_DESC -> "price_desc";
            case DIAMETRO_CRESC -> "diameter_asc";
            case RESISTENCIA_DESC -> "wr_desc";
        };
    }
}
