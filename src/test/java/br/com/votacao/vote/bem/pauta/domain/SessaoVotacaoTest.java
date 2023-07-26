package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.handler.APIException;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.application.service.ResultadoSessaoPublicador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoTest {

    private SessaoVotacao sessaoVotacao;

    @BeforeEach
    public void setup() {
        sessaoVotacao = new SessaoVotacao();
    }

    @Test
    public void testIniciaVotacao(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        SessaoVotacaoRequest request = new SessaoVotacaoRequest(Duration.ofMinutes(5));

        sessaoVotacao.iniciaVotacao(request, resultadoSessaoPublicador);

        assertEquals(StatusSessaoVotacao.ABERTA, sessaoVotacao.getStatus());
    }

    @Test
    public void testAdicionarVoto(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        SessaoVotacaoRequest request = new SessaoVotacaoRequest(Duration.ofMinutes(5));

        sessaoVotacao.iniciaVotacao(request, resultadoSessaoPublicador);

        VotoRequest votoRequest = new VotoRequest("07764268500",OpcaoVoto.SIM);
        assertNotNull(sessaoVotacao.adicionarVoto(votoRequest, resultadoSessaoPublicador));
    }

    @Test
    public void testValidaSessaoAbertaErro(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        assertThrows(APIException.class, () -> sessaoVotacao.validaSessaoAberta(resultadoSessaoPublicador));
    }

    @Test
    public void testAtualizaStatus(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        assertFalse(sessaoVotacao.atualizaStatus(resultadoSessaoPublicador));
    }

    @Test
    public void testValidaVotoErro(ResultadoSessaoPublicador resultadoSessaoPublicador) {
        SessaoVotacaoRequest request = new SessaoVotacaoRequest(Duration.ofMinutes(5));

        sessaoVotacao.iniciaVotacao(request,resultadoSessaoPublicador);

        VotoRequest votoRequest = new VotoRequest("07764268500",OpcaoVoto.NAO);
        sessaoVotacao.adicionarVoto(votoRequest,resultadoSessaoPublicador);

        APIException exception = assertThrows(APIException.class, () -> sessaoVotacao.adicionarVoto(votoRequest,resultadoSessaoPublicador));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusException());
        assertEquals("Este CPF já votou nesta sessão de votação.", exception.getMessage());
    }

    @Test
    public void testValidaSessaoAbertaPendente() {
        APIException exception = assertThrows(APIException.class, () -> sessaoVotacao.validaSessaoAberta());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusException());
        assertEquals("A sessão de votação não foi aberta para esta pauta.", exception.getMessage());
    }

    @Test
    public void testValidaSessaoAbertaFechada(ResultadoSessaoPublicador resultadoSessaoPublicador) throws InterruptedException {
        SessaoVotacaoRequest request = new SessaoVotacaoRequest(Duration.ofSeconds(1));

        sessaoVotacao.iniciaVotacao(request,resultadoSessaoPublicador);

        // aguarda o tempo da sessão expirar
        Thread.sleep(2000);

        APIException exception = assertThrows(APIException.class, () -> sessaoVotacao.validaSessaoAberta());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusException());
        assertEquals("A votação para esta pauta já foi encerrada.", exception.getMessage());
    }
}