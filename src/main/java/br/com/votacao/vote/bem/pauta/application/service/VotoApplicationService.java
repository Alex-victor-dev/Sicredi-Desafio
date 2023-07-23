package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.handler.APIException;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoResponse;
import br.com.votacao.vote.bem.pauta.application.repository.VotoRepository;
import br.com.votacao.vote.bem.pauta.domain.Voto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Log4j2
@Service
@RequiredArgsConstructor
public class VotoApplicationService implements VotoService {
    private final PautaRepository pautaRepository;

    @Override
    public VotoResponse registraVoto(UUID idPauta, VotoRequest votoRequest) {
        log.info( "[inicia] VotoApplicationService - registraVoto" );
        Pauta pauta = pautaRepository.buscaPaltaPorId( idPauta );
        Voto voto = pauta.adicionaVoto(votoRequest);
        pautaRepository.salvaPauta( pauta );
        log.info( "[inicia] VotoApplicationService - registraVoto" );
        return new VotoResponse( idPauta, votoRequest.getCpf(), voto );
    }
}
