package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.SessaoVotacaoResponse;

import java.util.UUID;

public interface SessaoService {
    SessaoVotacaoResponse abreSessaoVotacao(UUID idPauta, SessaoVotacaoRequest request);
}
