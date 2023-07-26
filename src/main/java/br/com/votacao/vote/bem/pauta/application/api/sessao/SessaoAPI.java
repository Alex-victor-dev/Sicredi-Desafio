package br.com.votacao.vote.bem.pauta.application.api.sessao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/pauta/{idPauta}/sessoes/abertura")
public interface SessaoAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    SessaoVotacaoResponse abreSessaoVotacao(@PathVariable UUID idPauta, @RequestBody SessaoVotacaoRequest sessaoVotacaoRequest);

}
