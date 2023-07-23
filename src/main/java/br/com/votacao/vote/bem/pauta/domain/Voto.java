package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("votos")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Voto {

    @Id
    private UUID idVoto;
    private UUID idVotoPauta;

    @CPF
    private String cpf;
    private OpcaoVoto opcaoVoto;
    private int numeroDeVotos;
    private LocalDateTime dataVoto;

    public Voto(VotoRequest request, UUID idPalta) {
        this.idVoto = UUID.randomUUID();
        this.idVotoPauta = idPalta;
        this.cpf = request.getCpf();
        this.dataVoto = LocalDateTime.now();
        this.opcaoVoto = request.getOpcaoVoto();
    }

}
