package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.StatusVotante;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class PessoaValidacaoResponse {
    private StatusVotante status;

    public boolean estaApto() {
        return status.equals( StatusVotante.ABLE_TO_VOTE );
    }
}
