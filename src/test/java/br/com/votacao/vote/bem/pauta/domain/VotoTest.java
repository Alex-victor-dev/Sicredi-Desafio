package br.com.votacao.vote.bem.pauta.domain;

import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class VotoTest {

    private Voto voto;

    @Test
    void testVotoConstructor() {
        // Prepare test data
        VotoRequest votoRequest = new VotoRequest("07764268500", OpcaoVoto.SIM);

        // Call the constructor
        Voto voto = new Voto(votoRequest);

        // Assert that the object is created correctly
        assertEquals("07764268500", voto.getCpf());
        assertEquals(OpcaoVoto.SIM, voto.getOpcaoVoto());
        assertNotNull(voto.getMomento());
    }

}