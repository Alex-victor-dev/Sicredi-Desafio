package br.com.votacao.vote.bem.voto.infra;

import br.com.votacao.vote.bem.voto.application.repository.VotoRepository;
import br.com.votacao.vote.bem.voto.domain.Voto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@RequiredArgsConstructor
public class VotoInfraRepository implements VotoRepository {
    private final VotoInfraMongoRepository votoInfraMongoRepository;

    @Override
    public Voto registraVoto(Voto voto) {
        log.info( "[inicia] VotoInfraRepository - registraVoto" );
        votoInfraMongoRepository.save( voto );
        log.info( "[finaliza] VotoInfraRepository - registraVoto " );
        return voto;
    }
}
