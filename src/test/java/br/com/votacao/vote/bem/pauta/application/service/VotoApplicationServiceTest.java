package br.com.votacao.vote.bem.pauta.application.service;
import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.ResultadoResponse;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoResponse;
import br.com.votacao.vote.bem.pauta.application.repository.PautaRepository;
import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.SessaoVotacao;
import br.com.votacao.vote.bem.pauta.domain.Voto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotoApplicationServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private VotoApplicationService votoService;

    @Captor
    private ArgumentCaptor<Pauta> pautaCaptor;

    @Test
    void testRegistraVoto() {
        // Prepare test data
        UUID idPauta = UUID.randomUUID();
        VotoRequest votoRequest = new VotoRequest("12345678901", OpcaoVoto.SIM);
        Pauta pauta = new Pauta(new PautaRequest("Teste", "DRIVE"));
        pauta.setIdPauta(idPauta);
        pauta.abreSessaoVotacao(new SessaoVotacaoRequest(Duration.ofMinutes(10)));
        when(pautaRepository.buscaPaltaPorId(idPauta)).thenReturn(pauta);
        when(pautaRepository.buscaPaltaPorId(idPauta)).thenReturn(pauta);
        // Call the service method
        VotoResponse votoResponse = votoService.registraVoto(idPauta, votoRequest);
        // Assert the response
        assertEquals(idPauta, votoResponse.getIdPauta());
        assertEquals(votoRequest.getCpf(), votoResponse.getCpf());
        assertEquals(OpcaoVoto.SIM, votoResponse.getOpcaoVoto());
        // Verify that the Voto object was added to the Pauta and saved with the repository
        verify(pautaRepository, times(1)).salvaPauta(pautaCaptor.capture());
        Pauta capturedPauta = pautaCaptor.getValue();
        assertNotNull(capturedPauta);
        Map<String, Voto> votos = capturedPauta.getSessaoVotacao().getVotos();
        assertEquals(1, votos.size());
        Voto capturedVoto = votos.get(votoRequest.getCpf());
        assertNotNull(capturedVoto);
        assertEquals(votoRequest.getOpcaoVoto(), capturedVoto.getOpcaoVoto());
    }

    @Test
    void testCalculaResultado() {
        // Prepare test data
        UUID idPauta = UUID.randomUUID();
        Pauta pauta = new Pauta(new PautaRequest( "123","1535"));
        pauta.setIdPauta(idPauta);
        Map<String, Voto> votos = new HashMap<>();
        votos.put("12345678901", new Voto(new VotoRequest("07764268500", OpcaoVoto.SIM)));
        votos.put("98765432101", new Voto(new VotoRequest("07764268500", OpcaoVoto.NAO)));
        pauta.getSessaoVotacao().setVotos(votos);
        when(pautaRepository.buscaPaltaPorId(idPauta)).thenReturn(pauta);

        // Call the service method
        ResultadoResponse resultadoResponse = votoService.calculaResultado(idPauta);

        // Assert the response
        assertEquals(1, resultadoResponse.getVotosSim());
        assertEquals(1, resultadoResponse.getVotosNao());
        assertEquals(2, resultadoResponse.getTotalDeVotos());

        // Verify that the PautaRepository method was called with the correct id
        verify(pautaRepository, times(1)).buscaPaltaPorId(idPauta);
    }
}
