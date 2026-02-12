package selton.dev.desafio_relogios.mapper;

import org.springframework.stereotype.Component;

import selton.dev.desafio_relogios.dto.RelogioDTO;
import selton.dev.desafio_relogios.dto.request.AtualizarRelogioRequest;
import selton.dev.desafio_relogios.dto.request.CriarRelogioRequest;
import selton.dev.desafio_relogios.model.Relogio;
import selton.dev.desafio_relogios.model.enums.EtiquetaResistenciaAgua;
import selton.dev.desafio_relogios.model.enums.MaterialCaixa;
import selton.dev.desafio_relogios.model.enums.TipoMovimento;
import selton.dev.desafio_relogios.model.enums.TipoVidro;

@Component
public class RelogioMapper {
    
    public Relogio toEntity(CriarRelogioRequest request) {
        Relogio entity = new Relogio();
        entity.setMarca(request.marca());
        entity.setModelo(request.modelo());
        entity.setReferencia(request.referencia());
        entity.setTipoMovimento(TipoMovimento.fromApi(request.tipoMovimento()));
        entity.setMaterialCaixa(MaterialCaixa.fromApi(request.materialCaixa()));
        entity.setTipoVidro(TipoVidro.fromApi(request.tipoVidro()));
        entity.setResistenciaAguaM(request.resistenciaAguaM());
        entity.setDiametroMm(request.diametroMm());
        entity.setLugToLugMm(request.lugToLugMm());
        entity.setEspessuraMm(request.espessuraMm());
        entity.setLarguraLugMm(request.larguraLugMm());
        entity.setPrecoEmCentavos(request.precoEmCentavos());
        entity.setUrlImage(request.urlImagem());
        return entity;
    }

    public RelogioDTO toDTO(Relogio entity) {
        return new RelogioDTO(
            entity.getId(),
            entity.getMarca(),
            entity.getModelo(),
            entity.getReferencia(),
            entity.getTipoMovimento().toApi(),
            entity.getMaterialCaixa().toApi(),
            entity.getTipoVidro().toApi(),
            entity.getResistenciaAguaM(),
            entity.getDiametroMm(),
            entity.getLugToLugMm(),
            entity.getEspessuraMm(),
            entity.getLarguraLugMm(),
            entity.getPrecoEmCentavos(),
            entity.getUrlImage(),
            getEtiqueta(entity.getResistenciaAguaM()),
            getPontuacao(entity)
        );
    }

    public void atualizarFromDto(AtualizarRelogioRequest request, Relogio entity) {
        entity.setMarca(request.marca() != null ? request.marca() : entity.getMarca());
        entity.setModelo(request.modelo() != null ? request.modelo() : entity.getModelo());
        entity.setReferencia(request.referencia() != null ? request.referencia() : entity.getReferencia());
        entity.setTipoMovimento(
            request.tipoMovimento() != null ? TipoMovimento.fromApi(request.tipoMovimento())
            : entity.getTipoMovimento()
        );
        entity.setMaterialCaixa(
            request.materialCaixa() != null ? MaterialCaixa.fromApi(request.materialCaixa())
            : entity.getMaterialCaixa()
        );
        entity.setTipoVidro(
            request.tipoVidro() != null ? TipoVidro.fromApi(request.tipoVidro())
            : entity.getTipoVidro()
        );
        entity.setResistenciaAguaM(
            request.resistenciaAguaM() != null ? request.resistenciaAguaM()
            : entity.getResistenciaAguaM()
        );
        entity.setDiametroMm(request.diametroMm() != null ? request.diametroMm() : entity.getDiametroMm());
        entity.setLugToLugMm(request.lugToLugMm() != null ? request.lugToLugMm() : entity.getLugToLugMm());
        entity.setEspessuraMm(request.espessuraMm() != null ? request.espessuraMm() : entity.getEspessuraMm());
        entity.setLarguraLugMm(request.larguraLugMm() != null ? request.larguraLugMm() : entity.getLarguraLugMm());
        entity.setPrecoEmCentavos(request.precoEmCentavos() != null ? request.precoEmCentavos() : entity.getPrecoEmCentavos());
        entity.setUrlImage(request.urlImagem() != null ? request.urlImagem() : entity.getUrlImage());
    }

    private String getEtiqueta(int valor) {
        if (valor < 50) {
            return EtiquetaResistenciaAgua.RESPINGOS.toApi();
        } else if (valor >= 50 && valor < 100) {
            return EtiquetaResistenciaAgua.USO_DIARIO.toApi();
        } else if (valor >= 100 && valor < 200) {
            return EtiquetaResistenciaAgua.NATACAO.toApi();
        } else {
            return EtiquetaResistenciaAgua.MERGULHO.toApi();
        }
    }

    private int getPontuacao(Relogio relogio) {
        int total = 0;
        if (relogio.getTipoVidro().equals(TipoVidro.SAFIRA)) total += 25;
        if (relogio.getResistenciaAguaM() >= 100) total += 15;
        if (relogio.getResistenciaAguaM() >= 200) total += 10;
        if (relogio.getTipoMovimento().equals(TipoMovimento.AUTOMATICO)) total += 20;
        if (relogio.getMaterialCaixa().equals(MaterialCaixa.ACO)) total += 10;
        if (relogio.getDiametroMm() >= 38 && relogio.getDiametroMm() <= 42) total += 8;
        return total;
    }

}
