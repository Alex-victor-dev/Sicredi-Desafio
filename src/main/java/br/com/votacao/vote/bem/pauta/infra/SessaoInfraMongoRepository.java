package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SessaoInfraMongoRepository extends MongoRepository <SessaoVotacao, UUID> {
}
