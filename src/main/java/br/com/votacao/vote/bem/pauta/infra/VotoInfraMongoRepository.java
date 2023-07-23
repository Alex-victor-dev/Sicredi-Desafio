package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.pauta.domain.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface VotoInfraMongoRepository extends MongoRepository<Voto, UUID> {
}
