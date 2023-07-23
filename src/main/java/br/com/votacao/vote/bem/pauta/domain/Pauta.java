package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "pautas")
@Getter
@Setter
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
    private List<Voto> votos = new ArrayList<>();
    private SessaoVotacao sessaoVotacao;

    public Pauta(PautaRequest request) {
        this.idPauta = UUID.randomUUID();
        this.titulo = request.getTitulo();
        this.descricao = request.getDescricao();
    }

}
