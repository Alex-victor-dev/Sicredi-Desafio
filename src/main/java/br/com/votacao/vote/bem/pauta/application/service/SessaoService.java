package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoResponse;

import java.util.UUID;

public interface SessaoService {
    SessaoVotacaoResponse abreSessaoVotacao(UUID idPauta, SessaoVotacaoRequest request);
}
