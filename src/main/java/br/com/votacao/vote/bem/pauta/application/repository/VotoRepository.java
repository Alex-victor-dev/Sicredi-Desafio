package br.com.votacao.vote.bem.pauta.application.repository;

import br.com.votacao.vote.bem.pauta.domain.Voto;

public interface VotoRepository {
    Voto registraVoto(Voto voto);
}
