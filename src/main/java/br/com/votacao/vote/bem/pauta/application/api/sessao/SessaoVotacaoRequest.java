package br.com.votacao.vote.bem.pauta.application.api.sessao;

import br.com.votacao.vote.bem.config.DurationDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class SessaoVotacaoRequest {
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration duracao;

    public SessaoVotacaoRequest(long duracaoEmSegundos) {
        this.duracao = Duration.parse(Long.toString(duracaoEmSegundos) + "s");
    }
}
