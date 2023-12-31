package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import br.com.votacao.vote.bem.pauta.domain.Voto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ResultadoResponse {
    private long votosSim;
    private long votosNao;
    private long totalDeVotos;

    public ResultadoResponse(SessaoVotacao sessaoVotacao) {
        Map<String, Voto> votos = sessaoVotacao.getVotos();

        this.votosSim = votos.values().stream()
                .filter( voto -> voto.getOpcaoVoto() == OpcaoVoto.SIM )
                .count();

        this.votosNao = votos.values().stream()
                .filter( voto -> voto.getOpcaoVoto() == OpcaoVoto.NAO )
                .count();

        this.totalDeVotos = votosSim+votosNao;

    }
}
