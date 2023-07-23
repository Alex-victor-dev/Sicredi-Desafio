package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaDetalhadaResponse;
import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaResponse;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class PautaApplicationService implements PautaService {
    private final PautaRepository pautaRepository;

    @Override
    public PautaResponse criaPauta(PautaRequest request) {
        log.info( "[inicia] PautaApplicationService - criaPauta");
        Pauta pauta = pautaRepository.salvaPauta(new Pauta(request));
        log.info( "[finaliza] PautaApplicationService - criaPauta");
        return PautaResponse.builder()
                .idPauta(pauta.getIdPauta())
                .build();
    }

    @Override
    public PautaDetalhadaResponse buscaPaltaPorId(UUID idPauta) {
        log.info( "[inicia] PautaApplicationService - buscaPaltaPorId");
        Pauta pauta = pautaRepository.buscaPaltaPorId(idPauta);
        log.info( "[finaliza] PautaApplicationService - buscaPaltaPorId");
        return new PautaDetalhadaResponse(pauta);
    }
}
