package br.com.votacao.vote.bem.voto.infra;

import br.com.votacao.vote.bem.voto.domain.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface VotoInfraMongoRepository extends MongoRepository<Voto, UUID> {
}
