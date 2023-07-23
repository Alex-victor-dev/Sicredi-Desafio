package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.handler.APIException;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PautaInfraRepository implements PautaRepository {

   private final PautaInfraMongoRepository pautaInfraMongoRepository;

    @Override
    public Pauta salvaPauta(Pauta pauta) {
        log.info( "[inicia] PautaInfraRepository - criaPauta");
        pautaInfraMongoRepository.save(pauta);
        log.info( "[finaliza] PautaInfraRepository - criaPauta");
        return pauta;
    }

    @Override
    public Pauta buscaPaltaPorId(UUID idPauta) {
        log.info( "[inicia] PautaInfraRepository - buscaPaltaPorId");
        Pauta pauta = pautaInfraMongoRepository.findById(idPauta)
                .orElseThrow(() ->APIException.build( HttpStatus.NOT_FOUND, "Pauta nao encontrada"));
        log.info( "[finaliza] PautaInfraRepository - buscaPaltaPorId");
        return pauta;
    }
    @Override
    public List<Pauta> buscarPautasAbertas() {
        log.info("[inicia] PautaInfraRepository - buscarPautasAbertas");
        List<Pauta> pautasAbertas = this.pautaInfraMongoRepository.buscarPautasAbertas();
        log.info("[finaliza] PautaInfraRepository - buscarPautasAbertas");
        return pautasAbertas;
    }

    @Override
    public void salvaPautas(List<Pauta> pautas) {
        log.info("[start] PautaInfraRepository - salvaPautas");
        this.pautaInfraMongoRepository.saveAll(pautas);
        log.info("[finish] PautaInfraRepository - salvaPautas");
    }
}


