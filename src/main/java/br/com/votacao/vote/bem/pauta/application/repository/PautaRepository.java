package br.com.votacao.vote.bem.pauta.application.repository;

import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;

import java.util.UUID;

public interface PautaRepository {
    Pauta salvaPauta(Pauta pauta);

    Pauta buscaPaltaPorId(UUID idPauta);

    SessaoVotacao abreSessaoVotacao(SessaoVotacao sessaoVotacao);
}
