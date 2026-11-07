package com.rica.investigadoresservice.investigadores.adaptadores.salida.mensajeria;

import com.rica.investigadoresservice.investigadores.aplicacion.PublicadorEventos;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Component
public class OutboxPublicadorEventos implements PublicadorEventos {

    private final OutboxEventRepository repositorio;
    private final ObjectMapper objectMapper;

    public OutboxPublicadorEventos(
            OutboxEventRepository repositorio,
            ObjectMapper objectMapper) {
        this.repositorio = repositorio;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publicar(
            String topico,
            String clave,
            Map<String, Object> contenido) {
        Map<String, Object> contenidoConTraza = new HashMap<>(contenido);
        String correlationId = MDC.get("correlationId");
        if (correlationId != null) {
            contenidoConTraza.put("correlationId", correlationId);
        }
        String payload = objectMapper.writeValueAsString(contenidoConTraza);
        repositorio.save(new OutboxEvent(topico, clave, payload));
    }
}