package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import br.com.votacao.vote.bem.pauta.domain.Voto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class VotoResponse {
    private UUID idPauta;
    private OpcaoVoto opcaoVoto;
    private LocalDateTime momento;
    private String cpf;

    public VotoResponse(UUID idPauta, String cpf, Voto voto) {
        this.idPauta = idPauta;
        this.opcaoVoto = voto.getOpcaoVoto();
        this.momento = voto.getMomento();
        this.cpf = cpf;
    }
}
