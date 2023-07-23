package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class VotoResponse {
    private UUID idVoto;
    private OpcaoVoto opcaoVoto;
    private LocalDateTime dataVoto;
}
