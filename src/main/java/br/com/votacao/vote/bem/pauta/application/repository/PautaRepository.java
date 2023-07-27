package br.com.votacao.vote.bem.pauta.application.repository;

import br.com.votacao.vote.bem.pauta.domain.Pauta;

import java.util.List;
import java.util.UUID;

public interface PautaRepository {
    Pauta salvaPauta(Pauta pauta);

    Pauta buscaPaltaPorId(UUID idPauta);

    List<Pauta> buscarPautasAbertas();

    void salvaPautas(List<Pauta> pautasAtualizadas);
}
