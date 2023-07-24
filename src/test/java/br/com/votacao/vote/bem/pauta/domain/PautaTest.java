import br.com.votacao.vote.bem.pauta.application.api.pauta.PautaRequest;
import br.com.votacao.vote.bem.pauta.application.api.sessao.SessaoVotacaoRequest;
import br.com.votacao.vote.bem.pauta.application.api.voto.VotoRequest;
import br.com.votacao.vote.bem.pauta.domain.OpcaoVoto;
import br.com.votacao.vote.bem.pauta.domain.Pauta;
import br.com.votacao.vote.bem.pauta.domain.StatusSessaoVotacao;
import br.com.votacao.vote.bem.pauta.domain.Voto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PautaTest {

    private Pauta pauta;

    @BeforeEach
    void setUp() {
        // Cria uma nova instância de Pauta antes de cada teste
        pauta = new Pauta(new PautaRequest("Título da Pauta", "Descrição da Pauta"));
    }

    @Test
    void testAbreSessaoVotacao() {
        // Testa o método abreSessaoVotacao

        // Cria uma nova SessaoVotacaoRequest com a duração de 2 minutos
        SessaoVotacaoRequest sessaoVotacaoRequest = new SessaoVotacaoRequest(120L);

        // Chama o método abreSessaoVotacao
        pauta.abreSessaoVotacao(sessaoVotacaoRequest);

        // Verifica se a sessão de votação foi aberta corretamente
    }

    @Test
    void testAdicionaVoto() {
        // Testa o método adicionaVoto

        // Cria um novo VotoRequest com a opção de voto SIM
        VotoRequest votoRequest = new VotoRequest("07764268500", OpcaoVoto.SIM);
        // Chama o método adicionaVoto
        Voto voto = pauta.adicionaVoto(votoRequest);

        // Verifica se o voto foi adicionado corretamente
        Assertions.assertNotNull(voto);
        Assertions.assertEquals(OpcaoVoto.SIM, voto.getOpcaoVoto());
    }

    @Test
    void testAtualizaStatusSessao() {
        // Testa o método atualizaStatusSessao

        // A sessão de votação deve ser inicialmente pendente
        Assertions.assertEquals( StatusSessaoVotacao.PENDENTE, pauta.getSessaoVotacao().getStatus());

        // Abre a sessão de votação com duração de 1 minuto
        pauta.abreSessaoVotacao(new SessaoVotacaoRequest(60L));

        // A sessão de votação deve estar aberta
        Assertions.assertEquals(StatusSessaoVotacao.ABERTA, pauta.getSessaoVotacao().getStatus());

        // Aguarda 1 minuto para que a sessão seja encerrada
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Atualiza o status da sessão
        boolean statusAtualizado = pauta.atualizaStatusSessao();

        // A sessão de votação deve estar fechada após atualizar o status
        Assertions.assertTrue(statusAtualizado);
        Assertions.assertEquals(StatusSessaoVotacao.FECHADA, pauta.getSessaoVotacao().getStatus());
    }
}
