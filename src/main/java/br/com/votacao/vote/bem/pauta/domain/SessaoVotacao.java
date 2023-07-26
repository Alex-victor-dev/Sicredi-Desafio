package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.config.DurationDeserializer;
import br.com.votacao.vote.bem.handler.APIException;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.ResultadoResponse;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.service.ResultadoSessaoPublicador;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class SessaoVotacao {
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration duracao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Map<String, Voto> votos;
    private StatusSessaoVotacao status;

    public SessaoVotacao() {
        this.status = StatusSessaoVotacao.PENDENTE;
    }


    void iniciaVotacao(SessaoVotacaoRequest request) {
        podeAbrirSessao();
        this.duracao = Optional.ofNullable(request.getDuracao()).orElse(Duration.ofMinutes(1));
        this.inicio = LocalDateTime.now();
        this.fim = inicio.plus(duracao);
        this.votos = new HashMap<>();
        this.status = StatusSessaoVotacao.ABERTA;
    }

    private void podeAbrirSessao() {
        atualizaStatus();
        if (status.equals(StatusSessaoVotacao.ABERTA)) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "A sessão de votação já foi aberta para esta pauta.");
        } else if (status.equals(StatusSessaoVotacao.FECHADA)) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "A votação para esta pauta já foi encerrada.");
        }
    }

    void validaSessaoAberta() {
        atualizaStatus();
        if (status.equals( StatusSessaoVotacao.PENDENTE )) {
            throw APIException.build( HttpStatus.BAD_REQUEST, "A sessão de votação não foi aberta para esta pauta." );
        } else if (status.equals( StatusSessaoVotacao.FECHADA )) {
            throw APIException.build( HttpStatus.BAD_REQUEST, "A votação para esta pauta já foi encerrada." );
        }
    }

    Voto adicionarVoto(VotoRequest votoRequest) {
        validaSessaoAberta();
        validaVoto( votoRequest );
        Voto voto = new Voto( votoRequest );
        this.votos.put( votoRequest.getCpf(), voto );
        return voto;
    }

    private void validaVoto(VotoRequest votoRequest) {
        if (this.votos.containsKey( votoRequest.getCpf() )) {
            throw APIException.build( HttpStatus.BAD_REQUEST, "Este CPF já votou nesta sessão de votação." );
        }
    }

    Boolean atualizaStatus(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        if (this.status == StatusSessaoVotacao.ABERTA) {
            LocalDateTime agora = LocalDateTime.now();
            if (agora.isAfter(fim)) {
                fechaSessao(resultadoSessaoPublicador);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private void fechaSessao(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        status = StatusSessaoVotacao.FECHADA;
        resultadoSessaoPublicador.publica(new ResultadoResponse(this));
    }

    public void setVotos(Map<String, Voto> votos) {
        this.votos = votos;
    }
}

