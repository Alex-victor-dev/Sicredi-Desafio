package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.voto.ResultadoResponse;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoResponse;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.Voto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Log4j2
@Service
@RequiredArgsConstructor
public class VotoApplicationService implements VotoService {
    private final PautaRepository pautaRepository;
    private final AssociadoValidador associadoValidador;
    private final ResultadoSessaoPublicador resultadoSessaoPublicador;

    @Override
    public VotoResponse registraVoto(UUID idPauta, VotoRequest votoRequest) {
        log.info( "[inicia] VotoApplicationService - registraVoto" );
        Pauta pauta = pautaRepository.buscaPaltaPorId( idPauta );
        associadoValidador.valida(votoRequest);
        Voto voto = pauta.adicionaVoto(votoRequest,resultadoSessaoPublicador);
        pautaRepository.salvaPauta( pauta );
        log.info( "[inicia] VotoApplicationService - registraVoto" );
        return new VotoResponse( idPauta, votoRequest.getCpf(), voto );
    }

    @Override
    public ResultadoResponse calculaResultado(UUID idPauta) {
        log.info( "[inicia] VotoApplicationService - calculaResultado" );
        Pauta pauta = pautaRepository.buscaPaltaPorId(idPauta);
        log.info( "[finaliza] VotoApplicationService - calculaResultado" );
        return new ResultadoResponse(pauta.getSessaoVotacao());
    }
}
