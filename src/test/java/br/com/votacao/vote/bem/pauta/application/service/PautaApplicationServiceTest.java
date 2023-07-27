package br.com.votacao.vote.bem.pauta.application.service;


import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaDetalhadaResponse;
import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaResponse;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaApplicationServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaApplicationService pautaService;

    @Captor
    private ArgumentCaptor<Pauta> pautaCaptor;

    @Test
    void testCriaPauta() {
        // Prepare test data
        PautaRequest pautaRequest = new PautaRequest("Título da Pauta", "Descrição da Pauta");
        Pauta pauta = new Pauta(pautaRequest);
        UUID generatedId = UUID.randomUUID();
        pauta.setIdPauta(generatedId);
        when(pautaRepository.salvaPauta(any(Pauta.class))).thenReturn(pauta);

        // Call the service method
        PautaResponse pautaResponse = pautaService.criaPauta(pautaRequest);

        // Assert the response
        assertNotNull(pautaResponse);
        assertEquals(generatedId, pautaResponse.getIdPauta());

        // Verify that the Pauta object was saved with the repository
        verify(pautaRepository, times(1)).salvaPauta(pautaCaptor.capture());
        Pauta capturedPauta = pautaCaptor.getValue();
        assertNotNull(capturedPauta);
        assertNotNull(capturedPauta.getIdPauta());
    }

    @Test
    void testBuscaPautaPorId() {
        // Prepare test data
        UUID idPauta = UUID.randomUUID();
        Pauta pauta = new Pauta(new PautaRequest("Título da Pauta", "Descrição da Pauta"));

        // Configurar comportamento do mock para retornar a pauta quando buscaPaltaPorId() é chamado
        when(pautaRepository.buscaPaltaPorId(idPauta)).thenReturn(pauta);

        // Call the service method
        PautaDetalhadaResponse pautaDetalhadaResponse = pautaService.buscaPaltaPorId(idPauta);

        // Assert the response
        assertNotNull(pautaDetalhadaResponse);
        assertEquals(pauta.getIdPauta(), pautaDetalhadaResponse.getIdPauta());

        // Verify that the PautaRepository method was called with the correct id
        verify(pautaRepository, times(1)).buscaPaltaPorId(idPauta);
    }
}
