package br.com.votacao.vote.bem.pauta.application.api.voto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/pautas/{idPauta}/resultado")
public interface ResultadoAPI {

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    ResultadoResponse calcularResultado(@PathVariable UUID idPauta);
}
