package br.com.votacao.vote.bem.pauta.application.api;

import br.com.votacao.vote.bem.pauta.domain.Pauta;
import lombok.Value;

import java.util.UUID;

@Value
public class PautaDetalhadaResponse {

    private UUID idPauta;
    private String titulo;
    private String descricao;

    public PautaDetalhadaResponse(Pauta pauta) {
        this.idPauta = pauta.getIdPauta();
        this.titulo = pauta.getTitulo();
        this.descricao = pauta.getDescricao();
    }
}
