package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaDetalhadaResponse;
import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaResponse;

import java.util.UUID;

public interface PautaService {
    PautaResponse criaPauta(PautaRequest pautaRequest);

    PautaDetalhadaResponse buscaPaltaPorId(UUID idPauta);
}
