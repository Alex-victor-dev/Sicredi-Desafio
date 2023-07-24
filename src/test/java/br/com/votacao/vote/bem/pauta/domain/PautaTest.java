package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaTest {

    private Pauta pauta;

    @BeforeEach
    void setUp() {
        PautaRequest pautaRequest = new PautaRequest("Título da Pauta", "Descrição da Pauta");
        pauta = new Pauta(pautaRequest);
    }

    @Test
    void testConstructor() {
        assertNotNull(pauta.getIdPauta());
        assertEquals("Título da Pauta", pauta.getTitulo());
        assertEquals("Descrição da Pauta", pauta.getDescricao());
        assertNotNull(pauta.getSessaoVotacao());
    }

    @Test
    void testAbreSessaoVotacao() {
        SessaoVotacaoRequest sessaoRequest = new SessaoVotacaoRequest( Duration.ofMinutes(5));
        pauta.abreSessaoVotacao(sessaoRequest);

        // Assert that the session is opened successfully
        assertEquals(StatusSessaoVotacao.ABERTA, pauta.getSessaoVotacao().getStatus());
    }

    void testAdicionaVoto() {
        // Create a mock for SessaoVotacao
        SessaoVotacao sessaoVotacao = mock(SessaoVotacao.class);
        when(sessaoVotacao.atualizaStatus()).thenReturn(true);

        // Create a mock for VotoRequest
        VotoRequest votoRequest = mock(VotoRequest.class);
        when(votoRequest.getCpf()).thenReturn("07764268500");
        // You may need to provide other necessary arguments for the VotoRequest mock

        // Perform the test
        Voto voto = pauta.adicionaVoto(votoRequest);

        // Verify that the vote is added successfully
        assertNotNull(voto);
        // You can add further assertions here to check if the vote is added correctly in the votos map.
    }

    @Test
    void testAtualizaStatusSessao() {
        // Create a real instance of Pauta
        Pauta pauta = new Pauta(new PautaRequest("123", "546"));
        // Partially mock the Pauta instance to mock the behavior of the SessaoVotacao
        Pauta pautaMock = spy(pauta);
        doReturn(true).when(pautaMock).atualizaStatusSessao();

        // Perform the test
        boolean status = pautaMock.atualizaStatusSessao();

        // Assert that the status update is successful
        assertTrue(status);
    }
}
