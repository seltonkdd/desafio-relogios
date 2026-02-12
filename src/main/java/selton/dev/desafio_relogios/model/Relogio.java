package selton.dev.desafio_relogios.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import selton.dev.desafio_relogios.model.enums.MaterialCaixa;
import selton.dev.desafio_relogios.model.enums.TipoMovimento;
import selton.dev.desafio_relogios.model.enums.TipoVidro;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "relogio", indexes = {
    @Index(name = "IDX_RELOGIO_MARCA", columnList = "marca"),
    @Index(name = "IDX_RELOGIO_CRIADOEM", columnList = "criadoEm"),
    @Index(name = "IDX_RELOGIO_PRECOCENTAVOS", columnList = "precoEmCentavos")
})
public class Relogio {
    
    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, length = 80)
    private String marca;

    @Column(nullable = false, length = 80)
    private String modelo;

    @Column(nullable = false, length = 80)  
    private String referencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) 
    private TipoMovimento tipoMovimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) 
    private MaterialCaixa materialCaixa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) 
    private TipoVidro tipoVidro;

    @Column(nullable = false)
    private Integer resistenciaAguaM;

    @Column(nullable = false)
    private Integer diametroMm;

    @Column(nullable = false)
    private Integer lugToLugMm;

    @Column(nullable = false)
    private Integer espessuraMm;

    @Column(nullable = false)
    private Integer larguraLugMm;

    @Column(nullable = false)
    private Long precoEmCentavos;

    @Column(nullable = false, length = 600)
    private String urlImage;

    @Column(nullable = false)
    private Instant criadoEm;

    @PrePersist
    void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (criadoEm == null) criadoEm = Instant.now();
        normalizar();
    }

    @PreUpdate
    void preUpdate() {
        normalizar();
    }

    void normalizar() {
        if (marca != null) marca.trim();
        if (modelo != null) modelo.trim();
        if (referencia != null) referencia.trim();
        if (urlImage != null) urlImage.trim();
    }
}
