package selton.dev.desafio_relogios.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import selton.dev.desafio_relogios.dto.RelogioDTO;
import selton.dev.desafio_relogios.dto.request.AtualizarRelogioRequest;
import selton.dev.desafio_relogios.dto.request.CriarRelogioRequest;
import selton.dev.desafio_relogios.dto.response.ResponseDTO;
import selton.dev.desafio_relogios.service.RelogioService;

@RestController
@RequestMapping("/api/relogios")
public class RelogioController {
    
    private final RelogioService service;

    public RelogioController(RelogioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RelogioDTO> post(@RequestBody CriarRelogioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelogioDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(
        @RequestParam(defaultValue = "1") Integer pagina,
        @RequestParam(defaultValue = "12") Integer porPagina,
        @RequestParam(required = false) String busca,
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) String modelo,
        @RequestParam(required = false) String referencia,
        @RequestParam(required = false) String tipoMovimento,
        @RequestParam(required = false) String tipoVidro,
        @RequestParam(required = false) String materialCaixa,
        @RequestParam(required = false) Integer resistenciaMin,
        @RequestParam(required = false) Integer resistenciaMax,
        @RequestParam(required = false) Long precoMin,
        @RequestParam(required = false) Long precoMax,
        @RequestParam(required = false) Integer diametroMin,
        @RequestParam(required = false) Integer diametroMax,
        @RequestParam(required = false) String ordenar
    ) {
        return ResponseEntity.ok(service.listar(
            pagina,
            porPagina,
            busca,
            marca,
            modelo,
            referencia,
            tipoMovimento,
            tipoVidro,
            materialCaixa,
            resistenciaMin,
            resistenciaMax,
            precoMin,
            precoMax,
            diametroMin,
            diametroMax,
            ordenar
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelogioDTO> update(@PathVariable UUID id, @RequestBody AtualizarRelogioRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
