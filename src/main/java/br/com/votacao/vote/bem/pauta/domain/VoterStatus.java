package br.com.votacao.vote.bem.pauta.domain;

public enum VoterStatus {
    ABLE_TO_VOTE( "ABLE_TO_VOTE", null ),
    UNABLE_TO_VOTE( "UNABLE_TO_VOTE", "O votante não está apto a votar." ),
    INVALID_CPF( "INVALID_CPF", "CPF inválido ou não encontrado na base de dados." );

    private final String status;
    private final String message;

    VoterStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
