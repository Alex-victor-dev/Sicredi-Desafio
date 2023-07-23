package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.application.service.VotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@Log4j2
@RestController
@RequiredArgsConstructor
public class VotoController implements VotoAPI {
    private final VotoService votoService;
    @Override
    public VotoResponse registraVoto(UUID idPauta, VotoRequest request) {
        log.info( "[inicia] VotoController - registraVoto");
        VotoResponse votoResponse = votoService.registraVoto(idPauta, request);
        log.info( "[finaliza] VotoController - registraVoto");
        return votoResponse;
    }
}
