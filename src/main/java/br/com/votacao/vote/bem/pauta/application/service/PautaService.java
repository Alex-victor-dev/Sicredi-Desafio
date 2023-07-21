package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.PautaDetalhadaResponse;
import br.com.votacao.vote.bem.pauta.application.api.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.PautaResponse;

import java.util.UUID;

public interface PautaService {
    PautaResponse criaPauta(PautaRequest request);

    PautaDetalhadaResponse buscaPaltaPorId(UUID idPauta);
}
