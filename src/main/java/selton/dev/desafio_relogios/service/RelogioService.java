package selton.dev.desafio_relogios.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import selton.dev.desafio_relogios.dto.RelogioDTO;
import selton.dev.desafio_relogios.dto.request.AtualizarRelogioRequest;
import selton.dev.desafio_relogios.dto.request.CriarRelogioRequest;
import selton.dev.desafio_relogios.dto.response.ResponseDTO;
import selton.dev.desafio_relogios.exception.custom.RelogioNaoEncontradoException;
import selton.dev.desafio_relogios.mapper.RelogioMapper;
import selton.dev.desafio_relogios.model.Relogio;
import selton.dev.desafio_relogios.model.enums.MaterialCaixa;
import selton.dev.desafio_relogios.model.enums.TipoMovimento;
import selton.dev.desafio_relogios.model.enums.TipoVidro;
import selton.dev.desafio_relogios.repository.RelogioRepository;
import selton.dev.desafio_relogios.service.enums.OrdenacaoRelogios;

@Service
@Validated
public class RelogioService {
    
    private final RelogioRepository repository;
    private final RelogioMapper mapper;

    public RelogioService(RelogioRepository repository, RelogioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RelogioDTO criar(@Valid CriarRelogioRequest request) {
        Relogio relogio = repository.save(mapper.toEntity(request));
        return mapper.toDTO(relogio);
    }

    public RelogioDTO buscar(UUID id) {
        Relogio relogio = repository.findById(id).orElseThrow(
            () -> new RelogioNaoEncontradoException("Relógio não encontrado: " + id)
        );
        return mapper.toDTO(relogio);
    }

    public RelogioDTO atualizar(UUID id, AtualizarRelogioRequest request) {
        Relogio relogio = repository.findById(id).orElseThrow(
            () -> new RelogioNaoEncontradoException("Relógio não encontrado: " + id)
        );
        mapper.atualizarFromDto(request, relogio);
        relogio = repository.save(relogio);
        return mapper.toDTO(relogio);
    }

    public void deletar(UUID id) {
        Relogio relogio = repository.findById(id).orElseThrow(
            () -> new RelogioNaoEncontradoException("Relógio não encontrado: " + id)
        );
        repository.delete(relogio);
    }

    public ResponseDTO listar(
        Integer pagina, 
        Integer porPagina,
        String busca,
        String marca,
        String modelo,
        String referencia,
        String tipoMovimento,
        String tipoVidro,
        String materialCaixa,
        Integer resistenciaMin,
        Integer resistenciaMax,
        Long precoMin,
        Long precoMax,
        Integer diametroMin,
        Integer diametroMax,
        String ordenar
    ) {

        int paginaDefault = Math.max(1, pagina);
        int porPaginaDefault = Math.min(60, Math.max(1, porPagina));

        TipoMovimento movimento = TipoMovimento.fromApi(tipoMovimento);
        TipoVidro vidro = TipoVidro.fromApi(tipoVidro);
        MaterialCaixa caixa = MaterialCaixa.fromApi(materialCaixa);
        OrdenacaoRelogios ordenacao = OrdenacaoRelogios.fromApi(ordenar);

        Sort sort = switch (ordenacao) {
            case MAIS_RECENTES -> Sort.by(Sort.Direction.DESC, "criadoEm");
            case PRECO_CRESC -> Sort.by(Sort.Direction.ASC, "precoEmCentavos");
            case PRECO_DESC -> Sort.by(Sort.Direction.DESC, "precoEmCentavos");
            case DIAMETRO_CRESC -> Sort.by(Sort.Direction.ASC, "diametroMm");
            case RESISTENCIA_DESC -> Sort.by(Sort.Direction.DESC, "resistenciaAguaM");
        };

        Pageable pageable = PageRequest.of(paginaDefault - 1, porPaginaDefault, sort);
        Specification<Relogio> spec = Specification.where(RelogioSpecs.busca(busca))
            .and(RelogioSpecs.marcaIgual(marca))
            .and(RelogioSpecs.modeloIgual(modelo))
            .and(RelogioSpecs.referenciaIgual(referencia))
            .and(RelogioSpecs.tipoMovimentoIgual(movimento))
            .and(RelogioSpecs.materialCaixaIgual(caixa))
            .and(RelogioSpecs.tipoVidroIgual(vidro))
            .and(RelogioSpecs.resistenciaAguaEntre(resistenciaMin, resistenciaMax))
            .and(RelogioSpecs.precoEntre(precoMin, precoMax))
            .and(RelogioSpecs.diametroEntre(diametroMin, diametroMax));
        
        Page<Relogio> relogios = repository.findAll(spec, pageable);

        return new ResponseDTO(
            relogios.getContent().stream().map(mapper::toDTO).toList(),
            relogios.getNumberOfElements()
        );
    }

}
