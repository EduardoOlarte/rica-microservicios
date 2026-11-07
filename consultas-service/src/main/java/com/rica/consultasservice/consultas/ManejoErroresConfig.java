package com.rica.consultasservice.consultas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import tools.jackson.core.JacksonException;

@Configuration
public class ManejoErroresConfig {

    @Bean
    public DefaultErrorHandler manejadorDeErrores(KafkaTemplate<String, String> kafkaTemplate) {
        DeadLetterPublishingRecoverer recuperador = new DeadLetterPublishingRecoverer(kafkaTemplate);

        ExponentialBackOffWithMaxRetries espera = new ExponentialBackOffWithMaxRetries(3);
        espera.setInitialInterval(1_000);
        espera.setMultiplier(2.0);
        espera.setMaxInterval(8_000);

        DefaultErrorHandler manejador = new DefaultErrorHandler(recuperador, espera);
        manejador.addNotRetryableExceptions(JacksonException.class, NullPointerException.class);
        return manejador;
    }
}
