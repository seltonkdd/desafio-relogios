package selton.dev.desafio_relogios.model.enums;

public enum MaterialCaixa {
    ACO,
    TITANIO,
    RESINA,
    BRONZE,
    CERAMICA;

    public static MaterialCaixa fromApi(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return switch (value) {
            case "steel" -> ACO;
            case "titanium" -> TITANIO;
            case "resin" -> RESINA;
            case "bronze" -> BRONZE;
            case "ceramic" -> CERAMICA;
            default -> throw new IllegalArgumentException("Material da caixa inválido: " + value);
        };
    }

    public String toApi() {
        return switch (this) {
            case ACO -> "steel";
            case TITANIO -> "titanium";
            case RESINA -> "resin";
            case BRONZE -> "bronze";
            case CERAMICA -> "ceramic";
        };
    }
}
