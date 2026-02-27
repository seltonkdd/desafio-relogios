package selton.dev.desafio_relogios.config;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import selton.dev.desafio_relogios.model.Relogio;
import selton.dev.desafio_relogios.model.enums.MaterialCaixa;
import selton.dev.desafio_relogios.model.enums.TipoMovimento;
import selton.dev.desafio_relogios.model.enums.TipoVidro;
import selton.dev.desafio_relogios.repository.RelogioRepository;

@Configuration
@RequiredArgsConstructor
public class CarregadorDadosInicial {

    private final RelogioRepository repository;
    
    @Bean
    CommandLineRunner seedRelogios() {
        return args -> {
            if (repository.count() > 0) return;

            Instant agora = Instant.now();
            
            List<Relogio> relogios = List.of(
                new Relogio(
                    UUID.randomUUID(),
                    "Casio",
                    "F-9WD0",
                    "1234",
                    TipoMovimento.AUTOMATICO,
                    MaterialCaixa.CERAMICA,
                    TipoVidro.MINERAL,
                    17,
                    22,
                    10,
                    16,
                    30,
                    12313L,
                    "n/a",
                    agora.minusSeconds(50000)
                ),
                new Relogio(
                    UUID.randomUUID(),
                    "Seiko",
                    "Diver 200m",
                    "1235",
                    TipoMovimento.MANUAL,
                    MaterialCaixa.ACO,
                    TipoVidro.MINERAL,
                    30,
                    22,
                    10,
                    16,
                    30,
                    20000L,
                    "n/a",
                    agora.minusSeconds(30000)
                ),
                new Relogio(
                    UUID.randomUUID(),
                    "Citizen",
                    "F-0",
                    "1234",
                    TipoMovimento.QUARTZ,
                    MaterialCaixa.RESINA,
                    TipoVidro.MINERAL,
                    17,
                    22,
                    10,
                    16,
                    30,
                    12313L,
                    "n/a",
                    agora.minusSeconds(10000)
                )
            );

            repository.saveAll(relogios);
        };
    }

}