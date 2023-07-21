package br.com.votacao.vote.bem.pauta.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/pauta")
public interface PautaAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    PautaResponse criaPauta(@RequestBody @Valid PautaRequest request);

    @GetMapping("/{idPauta}")
    @ResponseStatus(code = HttpStatus.OK)
    PautaDetalhadaResponse detalhaPauta(@PathVariable UUID idPauta);
}
