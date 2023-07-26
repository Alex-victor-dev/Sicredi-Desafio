package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.pauta.application.api.voto.PessoaValicacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.PessoaValidacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "validadorCPF", url = "https://x8ki-letl-twmt.n7.xano.io/api:OoOlAvp5")
public interface PessoaClientFeign {

    @PostMapping("/pessoa/validacao/")
    PessoaValidacaoResponse validaPessoa(@RequestBody PessoaValicacaoRequest pessoa);
}