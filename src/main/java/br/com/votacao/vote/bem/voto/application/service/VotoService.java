package br.com.votacao.vote.bem.voto.application.service;

import br.com.votacao.vote.bem.voto.application.api.VotoRequest;
import br.com.votacao.vote.bem.voto.application.api.VotoResponse;

import java.util.UUID;

public interface VotoService {
    VotoResponse registraVoto(UUID idPauta, VotoRequest request);
}
