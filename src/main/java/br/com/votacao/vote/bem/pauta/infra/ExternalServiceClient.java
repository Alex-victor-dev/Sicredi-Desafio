package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.pauta.application.api.voto.VoterStatusResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userInfo", url = "https://user-info.herokuapp.com")
public interface ExternalServiceClient {

    @GetMapping("/users/{cpf}")
    VoterStatusResponse verificaCpfVotante(@PathVariable("cpf") String cpf);
}
