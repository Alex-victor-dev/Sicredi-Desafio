package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.SessaoVotacaoResponse;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class SessaoApplicationService implements SessaoService {

    private final PautaRepository pautaService;
    private final PautaRepository pautaRepository;
    @Override
    public SessaoVotacaoResponse abreSessaoVotacao(UUID idPauta, SessaoVotacaoRequest request) {
        log.info( "[inicia] SessaoApplicationService - abreSessaoVotacao");
        Pauta pauta = pautaService.buscaPaltaPorId(idPauta);
        SessaoVotacao sessaoVotacao = pautaRepository.abreSessaoVotacao(new SessaoVotacao(request, idPauta));
        pauta.setSessaoVotacao(sessaoVotacao);
        pautaRepository.salvaPauta(pauta);
        log.info( "[finaliza] SessaoApplicationService - abreSessaoVotacao");
        return SessaoVotacaoResponse.builder()
                .dataInicio(sessaoVotacao.getDataInicio())
                .dataEncerramento(sessaoVotacao.getDataEncerramento())
                .idSessao(sessaoVotacao.getIdSessao()).build();
    }
}