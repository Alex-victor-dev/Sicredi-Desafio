package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Voto {
    private OpcaoVoto opcaoVoto;
    private LocalDateTime momento;

    public Voto(VotoRequest request) {
        this.momento = LocalDateTime.now();
        this.opcaoVoto = request.getOpcaoVoto();
    }

}
