package br.com.votacao.vote.bem.pauta.application.api;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class PautaRequest {
    @NotBlank(message = "O título não pode estar em branco.")
    @Size(max = 100, message = "O título deve ter no máximo {max} caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 500, message = "A descrição deve ter no máximo {max} caracteres.")
    private String descricao;
}
