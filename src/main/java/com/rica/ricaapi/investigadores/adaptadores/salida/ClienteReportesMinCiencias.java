package com.rica.ricaapi.investigadores.adaptadores.salida;

import com.rica.ricaapi.investigadores.dominio.Investigador;
import com.rica.ricaapi.investigadores.puertos.salida.ReportadorMinCiencias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClienteReportesMinCiencias
        implements ReportadorMinCiencias {

    private static final Logger log =
            LoggerFactory.getLogger(ClienteReportesMinCiencias.class);

    @Override
    public void enviar(Investigador investigador) {
        log.info(
                "Enviando investigador a MinCiencias: {}",
                investigador.getCorreoInstitucional().valor()
        );
    }
}