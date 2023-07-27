package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.application.service.VotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ResultadoController implements ResultadoAPI {
    private final VotoService votoService;

    @Override
    public ResultadoResponse calcularResultado(UUID idPauta) {
        log.info( "[inicia] ResultadoController - calcularResultado");
        ResultadoResponse resultado = votoService.calculaResultado(idPauta);
        log.info( "[finaliza] ResultadoController - calcularResultado");
        return resultado;
    }
}
