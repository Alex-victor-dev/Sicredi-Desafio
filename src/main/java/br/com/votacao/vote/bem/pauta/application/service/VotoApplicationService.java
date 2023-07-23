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

    private Set<String> cpfsVotantesSessaoAtual = new HashSet<>();
    private static Set<String> cpfsVotantesGeral = new HashSet<>();
    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    @Override
    public VotoResponse registraVoto(UUID idPauta, VotoRequest request) {
        log.info( "[inicia] VotoApplicationService - registraVoto");
        Pauta pauta = pautaRepository.buscaPaltaPorId(idPauta);
        SessaoVotacao sessaoVotacao = pauta.getSessaoVotacao();
        if (sessaoVotacao == null) {
                throw APIException.build( HttpStatus.BAD_REQUEST,"A sessão de votação não foi aberta para esta pauta.");
            }
        sessaoVotacao.validarSessaoAberta(sessaoVotacao);
        sessaoVotacao.resetCpfVotante(request.getCpf(), cpfsVotantesSessaoAtual);
        Voto voto = votoRepository.registraVoto(new Voto(request,idPauta));
        pauta.getSessaoVotacao().adicionarVoto(voto, cpfsVotantesSessaoAtual, cpfsVotantesGeral);
        pautaRepository.salvaPauta(pauta);
        log.info( "[inicia] VotoApplicationService - registraVoto");
        return VotoResponse.builder()
                .idVoto(voto.getIdVoto())
                .opcaoVoto(voto.getOpcaoVoto())
                .dataVoto(voto.getDataVoto())
                .build();
    }
    }
