package br.com.votacao.vote.bem.pauta.infra;

import br.com.votacao.vote.bem.config.AwsConfigProperties;
import br.com.votacao.vote.bem.pauta.application.api.voto.ResultadoResponse;
import br.com.votacao.vote.bem.pauta.application.service.ResultadoSessaoPublicador;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class ResultadoSessaoSnsPublicador implements ResultadoSessaoPublicador {
    private final NotificationMessagingTemplate notificationMessagingTemplate;
    private final AwsConfigProperties properties;
    @Override
    public void publica(ResultadoResponse resultadoResponse) {
        log.info("[start] ResultadoSessaoSnsPublicador - publica");
        notificationMessagingTemplate.sendNotification(properties.getResultadoSessaoTopico(),resultadoResponse,resultadoResponse.toString());
        log.info("[finish] ResultadoSessaoSnsPublicador - publica");
    }
}
