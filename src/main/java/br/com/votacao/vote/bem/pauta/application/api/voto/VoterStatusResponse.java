package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.VoterStatus;
import lombok.Value;

@Value
public class VoterStatusResponse {
    private VoterStatus status;
}
