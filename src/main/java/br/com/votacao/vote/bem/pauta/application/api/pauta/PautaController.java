package br.com.votacao.vote.bem.pauta.application.api.pauta;

import br.com.votacao.vote.bem.pauta.application.service.PautaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PautaController implements PautaAPI {
    private final PautaService pautaService;

    @Override
    public PautaResponse criaPauta(PautaRequest pautaRequest) {
        log.info( "[inicia] PautaController - criaPauta");
        PautaResponse pauta = pautaService.criaPauta( pautaRequest );
        log.info( "[finaliza] PautaController - criaPauta");
        return pauta;
    }

    @Override
    public PautaDetalhadaResponse detalhaPauta(UUID idPauta) {
        log.info( "[inicia] PautaController - detalhaPauta");
        PautaDetalhadaResponse pautaDetalhadaResponse = pautaService.buscaPaltaPorId(idPauta);
        log.info( "[finaliza] PautaController - detalhaPauta");
        return pautaDetalhadaResponse;
    }
}
