package br.com.votacao.vote.bem.pauta.application.service;


import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoResponse;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoApplicationServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessaoApplicationService sessaoService;

    @Captor
    private ArgumentCaptor<Pauta> pautaCaptor;

    @Test
    void testAbreSessaoVotacao() {
        // Prepare test data
        UUID idPauta = UUID.randomUUID();
        SessaoVotacaoRequest sessaoVotacaoRequest = new SessaoVotacaoRequest(Duration.ofMinutes(10));
        Pauta pauta = new Pauta(new PautaRequest("Título da Pauta", "Descrição da Pauta"));
        when(pautaRepository.buscaPaltaPorId(idPauta)).thenReturn(pauta);

        // Call the service method
        SessaoVotacaoResponse sessaoVotacaoResponse = sessaoService.abreSessaoVotacao(idPauta, sessaoVotacaoRequest);

        // Assert the response
        assertNotNull(sessaoVotacaoResponse);
        assertEquals(idPauta, sessaoVotacaoResponse.getIdPauta());
        SessaoVotacao sessaoVotacao = pauta.getSessaoVotacao();
        assertNotNull(sessaoVotacao);
        assertEquals(sessaoVotacaoRequest.getDuracao(), sessaoVotacao.getDuracao());
        assertNotNull(sessaoVotacao.getInicio());
        assertEquals(sessaoVotacao.getInicio().plus(sessaoVotacaoRequest.getDuracao()), sessaoVotacao.getFim());

        // Verify that the Pauta object was saved with the repository
        verify(pautaRepository, times(1)).salvaPauta(pautaCaptor.capture());
        Pauta capturedPauta = pautaCaptor.getValue();
        assertNotNull(capturedPauta);
        assertEquals(sessaoVotacao, capturedPauta.getSessaoVotacao());
    }
}
