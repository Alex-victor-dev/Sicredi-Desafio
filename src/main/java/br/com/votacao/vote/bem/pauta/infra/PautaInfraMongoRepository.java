package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.pauta.domain.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PautaInfraMongoRepository extends MongoRepository <Pauta, UUID> {
}
