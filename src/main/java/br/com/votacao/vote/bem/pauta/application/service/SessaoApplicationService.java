package br.com.votacao.vote.bem.pauta.application.service;

import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoResponse;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class SessaoApplicationService implements SessaoService {
    private final ResultadoSessaoPublicador resultadoSessaoPublicador;
    private final PautaRepository pautaRepository;
    @Override
    public SessaoVotacaoResponse abreSessaoVotacao(UUID idPauta, SessaoVotacaoRequest sessaoVotacaoRequest) {
        log.info( "[inicia] SessaoApplicationService - abreSessaoVotacao");
        Pauta pauta = pautaRepository.buscaPaltaPorId(idPauta);
        pauta.abreSessaoVotacao(sessaoVotacaoRequest, resultadoSessaoPublicador);
        pautaRepository.salvaPauta(pauta);
        log.info( "[finaliza] SessaoApplicationService - abreSessaoVotacao");
        return new SessaoVotacaoResponse(pauta.getSessaoVotacao(), idPauta);
    }
}
