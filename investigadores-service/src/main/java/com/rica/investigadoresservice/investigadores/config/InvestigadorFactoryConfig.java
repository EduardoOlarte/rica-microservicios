package com.rica.investigadoresservice.investigadores.config;

import com.rica.investigadoresservice.investigadores.dominio.InvestigadorFactory;
import com.rica.investigadoresservice.investigadores.puertos.salida.RepositorioInvestigadores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvestigadorFactoryConfig {

    @Bean
    InvestigadorFactory investigadorFactory(RepositorioInvestigadores repositorioInvestigadores) {
        return new InvestigadorFactory(repositorioInvestigadores);
    }
}
