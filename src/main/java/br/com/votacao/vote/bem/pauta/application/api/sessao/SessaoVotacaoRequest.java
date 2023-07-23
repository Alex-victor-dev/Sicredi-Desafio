package br.com.votacao.vote.bem.pauta.application.api.sessao;

import br.com.votacao.vote.bem.config.DurationDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SessaoVotacaoRequest {
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration duracao;
}
