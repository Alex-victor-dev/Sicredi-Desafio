package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.voto.ResultadoResponse;

public interface ResultadoSessaoPublicador {
    void publica(ResultadoResponse resultadoResponse);
}
