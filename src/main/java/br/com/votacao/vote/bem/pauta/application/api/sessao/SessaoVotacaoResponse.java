package br.com.votacao.vote.bem.pauta.application.api.sessao;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SessaoVotacaoResponse {
    private UUID idPauta;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public SessaoVotacaoResponse(SessaoVotacao sessaoVotacao, UUID idPauta) {
        this.idPauta = idPauta;
        this.inicio = sessaoVotacao.getInicio();
        this.fim = sessaoVotacao.getFim();
    }
}
