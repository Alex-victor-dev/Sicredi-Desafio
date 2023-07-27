package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.service.ResultadoSessaoPublicador;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Document(collection = "pautas")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pauta {

    @Id
    private UUID idPauta;
    @NotBlank(message = "O título não pode estar em branco.")
    @Size(max = 100, message = "O título deve ter no máximo {max} caracteres.")
    private String titulo;
    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 500, message = "A descrição deve ter no máximo {max} caracteres.")
    private String descricao;
    private SessaoVotacao sessaoVotacao;

    public Pauta(PautaRequest pautaRequest) {
        this.idPauta = UUID.randomUUID();
        this.titulo = pautaRequest.getTitulo();
        this.descricao = pautaRequest.getDescricao();
        this.sessaoVotacao = new SessaoVotacao();
    }

    public void abreSessaoVotacao(SessaoVotacaoRequest sessaoVotacaoRequest, ResultadoSessaoPublicador resultadoSessaoPublicador) {
        this.sessaoVotacao.iniciaVotacao( sessaoVotacaoRequest, resultadoSessaoPublicador);
    }

    public Voto adicionaVoto(VotoRequest votoRequest, ResultadoSessaoPublicador resultadoSessaoPublicador) {
        return this.sessaoVotacao.adicionarVoto( votoRequest, resultadoSessaoPublicador);
    }

    public boolean atualizaStatusSessao(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        return this.sessaoVotacao.atualizaStatus(resultadoSessaoPublicador);
    }

    public void setIdPauta(UUID generatedId) {
        this.idPauta = generatedId;
    }
}


