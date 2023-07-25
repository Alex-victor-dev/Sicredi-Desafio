package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.voto.VoterStatusResponse;
import br.com.votacao.vote.bem.pauta.domain.VoterStatus;
import br.com.votacao.vote.bem.pauta.infra.ExternalServiceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotoVerifier {

    private final ExternalServiceClient externalServiceClient;

    public VoterStatus verificaElegibilidadeVotante(String cpf) {
        try {
            VoterStatusResponse response = externalServiceClient.verificaCpfVotante(cpf);
            return response.getStatus();
        } catch (FeignException.NotFound ex) {
            return VoterStatus.INVALID_CPF;
        }
    }
}
