package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import br.com.votacao.vote.bem.pauta.domain.Voto;
import lombok.Value;

import java.util.Map;

@Value
public class ResultadoResponse {
    private long votosSim;
    private long votosNao;

    public ResultadoResponse(SessaoVotacao sessaoVotacao) {
        Map<String, Voto> votos = sessaoVotacao.getVotos();

        this.votosSim = votos.values().stream()
                .filter( voto -> voto.getOpcaoVoto() == OpcaoVoto.SIM )
                .count();

        this.votosNao = votos.values().stream()
                .filter( voto -> voto.getOpcaoVoto() == OpcaoVoto.NAO )
                .count();

    }
}
