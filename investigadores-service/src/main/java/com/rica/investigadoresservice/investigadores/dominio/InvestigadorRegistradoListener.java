package com.rica.investigadoresservice.investigadores;

import com.rica.investigadoresservice.investigadores.dominio.InvestigadorRegistrado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InvestigadorRegistradoListener {

    private static final Logger log =
            LoggerFactory.getLogger(InvestigadorRegistradoListener.class);

    @EventListener
    public void cuandoSeRegistraInvestigador(
            InvestigadorRegistrado evento) {

        log.info(
                "Investigador registrado: correo={}, ocurridoEn={}",
                evento.correoInstitucional(),
                evento.ocurridoEn()
        );
    }
}