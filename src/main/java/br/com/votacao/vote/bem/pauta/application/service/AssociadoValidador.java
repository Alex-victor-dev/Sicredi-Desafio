package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;

public interface AssociadoValidador {

    void valida(VotoRequest votoRequest);
}
