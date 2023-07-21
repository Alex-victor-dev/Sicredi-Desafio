package br.com.votacao.vote.bem.voto.application.api;

import br.com.votacao.vote.bem.voto.domain.OpcaoVoto;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class VotoRequest {
    @CPF
    private String cpf;
    private OpcaoVoto opcaoVoto;
}
