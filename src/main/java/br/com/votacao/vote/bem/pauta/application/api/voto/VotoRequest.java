package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

@Value
public class VotoRequest {
    @CPF
    private String cpf;
    private OpcaoVoto opcaoVoto;
}
