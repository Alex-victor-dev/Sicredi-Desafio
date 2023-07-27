package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.application.service.ResultadoSessaoPublicador;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class FechadorSessoesScheduler {
    private final PautaRepository pautaRepository;
    private final ResultadoSessaoPublicador resultadoSessaoPublicador;

    @Scheduled(fixedRate = 60000)
    public void fecharSessoes() {
        log.info("[start] FechadorSessoesScheduler - fecharSessoes");
        List<Pauta> pautasAbertas = pautaRepository.buscarPautasAbertas();
        List<Pauta> pautasAtualizadas = pautasAbertas.stream()
                .filter(pauta -> pauta.atualizaStatusSessao(resultadoSessaoPublicador))
                .collect(Collectors.toList());
        pautaRepository.salvaPautas(pautasAtualizadas);
        log.info("[finish] FechadorSessoesScheduler - fecharSessoes");
    }
}
