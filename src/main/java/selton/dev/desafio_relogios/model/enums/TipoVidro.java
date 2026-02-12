package selton.dev.desafio_relogios.model.enums;

public enum TipoVidro {
    MINERAL,
    SAFIRA,
    ACRILICO;

    public static TipoVidro fromApi(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return switch (value) {
            case "mineral" -> MINERAL;
            case "sapphire" -> SAFIRA;
            case "acrylic" -> ACRILICO;
            default -> throw new IllegalArgumentException("Tipo de vidro inválido: " + value);
        };
    }

    public String toApi() {
        return switch (this) {
            case MINERAL -> "mineral";
            case SAFIRA -> "sapphire";
            case ACRILICO -> "acrylic";
        };
    }
}
