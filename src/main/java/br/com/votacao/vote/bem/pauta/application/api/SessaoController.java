package br.com.votacao.vote.bem.pauta.application.api;

import br.com.votacao.vote.bem.pauta.application.service.SessaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SessaoController implements SessaoAPI {

    private final SessaoService sessaoService;
    @Override
    public SessaoVotacaoResponse abreSessaoVotacao(UUID idPauta, SessaoVotacaoRequest request) {
        log.info( "[inicia] SessaoController - abreSessaoVotacao");
        SessaoVotacaoResponse sessaoVotacaoResponse = sessaoService.abreSessaoVotacao(idPauta, request);
        log.info( "[finaliza] SessaoController - abreSessaoVotacao");
        return sessaoVotacaoResponse;
    }
}
