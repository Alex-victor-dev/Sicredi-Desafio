package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.application.service.ResultadoSessaoPublicador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FechadorSessoesSchedulerTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private Pauta pauta1, pauta2, pauta3;

    @Test
    public void fecharSessoesTest(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        // Arrange
        when(pauta1.atualizaStatusSessao(resultadoSessaoPublicador)).thenReturn(true);
        when(pauta2.atualizaStatusSessao(resultadoSessaoPublicador)).thenReturn(false);
        when(pauta3.atualizaStatusSessao(resultadoSessaoPublicador)).thenReturn(true);
        List<Pauta> pautasAbertas = Arrays.asList(pauta1, pauta2, pauta3);
        when(pautaRepository.buscarPautasAbertas()).thenReturn(pautasAbertas);
      //  FechadorSessoesScheduler scheduler = new FechadorSessoesScheduler(pautaRepository);

        // Act
      //  scheduler.fecharSessoes();

        // Assert
        verify(pautaRepository, times(1)).buscarPautasAbertas();
        verify(pauta1, times(1)).atualizaStatusSessao(resultadoSessaoPublicador);
        verify(pauta2, times(1)).atualizaStatusSessao(resultadoSessaoPublicador);
        verify(pauta3, times(1)).atualizaStatusSessao(resultadoSessaoPublicador);
        verify(pautaRepository, times(1)).salvaPautas( Arrays.asList(pauta1, pauta3));
    }
}
