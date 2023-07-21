package br.com.votacao.vote.bem.voto.application.service;

import br.com.votacao.vote.bem.handler.APIException;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.application.service.PautaService;
import br.com.votacao.vote.bem.pauta.application.service.SessaoService;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import br.com.votacao.vote.bem.voto.application.api.VotoRequest;
import br.com.votacao.vote.bem.voto.application.api.VotoResponse;
import br.com.votacao.vote.bem.voto.application.repository.VotoRepository;
import br.com.votacao.vote.bem.voto.domain.Voto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Log4j2
@Service
@RequiredArgsConstructor
public class VotoApplicationService implements VotoService {
    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    @Override
    public VotoResponse registraVoto(UUID idPauta, VotoRequest request) {
        log.info( "[inicia] VotoApplicationService - registraVoto");
        Pauta pauta = pautaRepository.buscaPaltaPorId(idPauta);
        SessaoVotacao sessaoVotacao = pauta.getSessaoVotacao();
        sessaoVotacao.validarSessaoAberta(sessaoVotacao);
        Voto voto = votoRepository.registraVoto(new Voto(request,idPauta));
        pauta.getSessaoVotacao().adicionarVoto(voto);
        pautaRepository.salvaPauta(pauta);
        log.info( "[inicia] VotoApplicationService - registraVoto");
        return VotoResponse.builder()
                .idVoto(voto.getIdVoto())
                .opcaoVoto(voto.getOpcaoVoto())
                .dataVoto(voto.getDataVoto())
                .build();
    }


    }
