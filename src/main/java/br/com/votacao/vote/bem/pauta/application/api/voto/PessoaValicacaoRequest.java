package br.com.votacao.vote.bem.pauta.application.api.voto;


import lombok.Getter;

@Getter
public class PessoaValicacaoRequest {
    private String cpf;

    public PessoaValicacaoRequest(VotoRequest votoRequest) {
        this.cpf = votoRequest.getCpf();
    }
}
