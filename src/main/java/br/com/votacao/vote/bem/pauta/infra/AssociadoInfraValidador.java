package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.handler.APIException;
import br.com.votacao.vote.bem.pauta.application.api.voto.PessoaValicacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.PessoaValidacaoResponse;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.service.AssociadoValidador;
import br.com.votacao.vote.bem.pauta.domain.StatusVotante;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Log4j2
public class AssociadoInfraValidador implements AssociadoValidador {
    private final PessoaClientFeign pessoaClientFeign;

    @Override
    public void valida(VotoRequest votoRequest) {
        log.info( "[start] AssociadoInfraValidador - valida" );
        var validacao = integraPostValidacao( votoRequest );
        if (!validacao.estaApto()) {
            throw APIException.build( HttpStatus.BAD_REQUEST, StatusVotante.UNABLE_TO_VOTE.getMessage() );
        }
        log.info( "[finish] AssociadoInfraValidador - valida" );
    }

    PessoaValidacaoResponse integraPostValidacao(VotoRequest votoRequest) {
        try {
            return pessoaClientFeign.validaPessoa( new PessoaValicacaoRequest( votoRequest ) );
        } catch (ResponseStatusException e) {
            throw APIException.build( HttpStatus.BAD_REQUEST, StatusVotante.INVALID_CPF.getMessage() );
        } catch (Exception e) {
            throw new RuntimeException( "ERRO AO INTEGRA COM VALIDACAO PESSOA!", e );
        }
    }
}


