package br.com.votacao.vote.bem.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "br.com.votacao.vote.bem")
public class FeignConfig {
    // Configurações adicionais do Feign, se necessário
}
