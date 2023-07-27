package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.voto.ResultadoResponse;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoResponse;

import java.util.UUID;

public interface VotoService {
    VotoResponse registraVoto(UUID idPauta, VotoRequest request);

    ResultadoResponse calculaResultado(UUID idPauta);
}
