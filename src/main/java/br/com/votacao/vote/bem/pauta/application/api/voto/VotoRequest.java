package br.com.votacao.vote.bem.pauta.application.api.voto;

import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class VotoRequest {
    @CPF
    private String cpf;
    private OpcaoVoto opcaoVoto;
}
