package com.rica.consultasservice.plataforma;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    @Bean
    public NewTopic investigadoresRegistrados() {
        return TopicBuilder.name("investigadores.registrados").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic investigadoresEnEliminacion() {
        return TopicBuilder.name("investigadores.en-eliminacion").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic investigadoresEliminados() {
        return TopicBuilder.name("investigadores.eliminados").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic publicacionesRegistradas() {
        return TopicBuilder.name("publicaciones.registradas").partitions(1).replicas(1).build();
    }
}
