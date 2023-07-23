package br.com.votacao.vote.bem.pauta.application.api.voto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/pautas/{idPauta}/votos")
public interface VotoAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    VotoResponse registraVoto(@PathVariable UUID idPauta, @RequestBody @Valid VotoRequest request);
}
