package br.com.votacao.vote.bem.pauta.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusVotante {
    ABLE_TO_VOTE(null),
    UNABLE_TO_VOTE("O votante não está apto a votar."),
    INVALID_CPF("CPF inválido ou não encontrado na base de dados.");

    private final String message;
}