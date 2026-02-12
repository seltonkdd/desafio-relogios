package selton.dev.desafio_relogios.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import selton.dev.desafio_relogios.model.Relogio;

public interface RelogioRepository extends JpaRepository<Relogio, UUID>, JpaSpecificationExecutor<Relogio>{
    
}
