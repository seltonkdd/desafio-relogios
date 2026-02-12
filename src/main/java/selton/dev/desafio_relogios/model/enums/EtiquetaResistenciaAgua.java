package selton.dev.desafio_relogios.model.enums;

public enum EtiquetaResistenciaAgua {
    RESPINGOS,
    USO_DIARIO,
    NATACAO,
    MERGULHO;

    public String toApi() {
        return switch (this) {
            case RESPINGOS -> "respingos";
            case USO_DIARIO -> "uso_diario";
            case NATACAO -> "natacao";
            case MERGULHO -> "mergulho";
        };
    }
}
