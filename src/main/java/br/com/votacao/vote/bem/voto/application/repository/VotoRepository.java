package br.com.votacao.vote.bem.voto.application.repository;

import br.com.votacao.vote.bem.voto.domain.Voto;

public interface VotoRepository {
    Voto registraVoto(Voto voto);
}
