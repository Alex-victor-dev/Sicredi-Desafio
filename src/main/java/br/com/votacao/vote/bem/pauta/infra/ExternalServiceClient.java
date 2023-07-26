package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.pauta.application.api.voto.VoterStatusResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userinfo", url = "https://user-info.herokuapp.com")
public interface ExternalServiceClient {

    @GetMapping("/users/%s")
    VoterStatusResponse verificaCpfVotante(@RequestBody String cpf);
}
