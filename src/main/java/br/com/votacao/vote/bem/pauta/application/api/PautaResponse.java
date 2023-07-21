package br.com.votacao.vote.bem.pauta.application.api;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PautaResponse {
    private UUID idPauta;
}
