package selton.dev.desafio_relogios.dto.response;

import java.util.List;

import selton.dev.desafio_relogios.dto.RelogioDTO;

public record ResponseDTO(
    List<RelogioDTO> itens,
    Integer total
) {
    
}
