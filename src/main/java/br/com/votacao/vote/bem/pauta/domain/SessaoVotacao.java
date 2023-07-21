package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.handler.APIException;
import br.com.votacao.vote.bem.pauta.application.api.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.voto.domain.Voto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document("sessao_votacao")
public class SessaoVotacao {

    @Id
    private UUID idSessao;
    private UUID idPautaSessao;
    private int duracao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEncerramento;
    private List<Voto> votos;


    public SessaoVotacao(SessaoVotacaoRequest request, UUID idPauta) {
        this.idSessao = UUID.randomUUID();
        this.idPautaSessao = idPauta;
        this.duracao = Math.max(request.getDuracao(), 1);
        this.dataInicio = LocalDateTime.now();
        this.dataEncerramento = dataInicio.plusMinutes(duracao);
        this.votos = new ArrayList<>();
    }

    public boolean estaAbertaParaVotacao() {
        LocalDateTime now = LocalDateTime.now();
        return dataInicio != null && dataEncerramento != null &&
                dataInicio.isBefore(now) && dataEncerramento.isAfter(now);
    }
    public void validarSessaoAberta(SessaoVotacao sessaoVotacao) {
        if (sessaoVotacao == null) {
            throw APIException.build( HttpStatus.BAD_REQUEST,"A sessão de votação não foi aberta para esta pauta.");
        }

        if (!sessaoVotacao.estaAbertaParaVotacao()) {
            throw APIException.build(HttpStatus.BAD_REQUEST,"A votação para esta pauta já foi encerrada.");
        }
    }

    public void adicionarVoto(Voto voto) {
        votos.add(voto);
    }
}
