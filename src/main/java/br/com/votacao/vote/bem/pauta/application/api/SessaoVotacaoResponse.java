package br.com.votacao.vote.bem.pauta.application.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SessaoVotacaoResponse {

    private UUID idSessao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEncerramento;
}
