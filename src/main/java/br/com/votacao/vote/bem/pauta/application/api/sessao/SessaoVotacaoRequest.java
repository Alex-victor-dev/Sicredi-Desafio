package br.com.votacao.vote.bem.pauta.application.api.sessao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SessaoVotacaoRequest {
    private Duration duracao;
}
