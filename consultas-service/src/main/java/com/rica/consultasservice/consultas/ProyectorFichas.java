package com.rica.consultasservice.consultas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class ProyectorFichas {

    private static final Logger log = LoggerFactory.getLogger(ProyectorFichas.class);

    private final FichaRepository fichaRepository;
    private final ObjectMapper objectMapper;

    public ProyectorFichas(FichaRepository fichaRepository, ObjectMapper objectMapper) {
        this.fichaRepository = fichaRepository;
        this.objectMapper = objectMapper;
    }

    public synchronized void alRegistrarseInvestigador(String mensaje) {
        JsonNode evento = objectMapper.readTree(mensaje);
        String correo = textoRequerido(evento, "correoInstitucional");
        FichaInvestigador ficha = fichaOCrear(correo);
        ficha.registrar(textoRequerido(evento, "nombreCompleto"));
        fichaRepository.save(ficha);
        log.info("Proyección: ficha de {} actualizada (registro)", correo);
    }

    public synchronized void alRegistrarsePublicacion(String mensaje) {
        JsonNode evento = objectMapper.readTree(mensaje);
        String correo = textoRequerido(evento, "investigadorCorreo");
        FichaInvestigador ficha = fichaOCrear(correo);
        boolean agregada = ficha.agregarPublicacion(new ResumenPublicacion(
                textoRequerido(evento, "id"),
                textoRequerido(evento, "titulo"),
                textoRequerido(evento, "tipo"),
                enteroRequerido(evento, "anio")));
        if (agregada) {
            fichaRepository.save(ficha);
            log.info("Proyección: ficha de {} ahora tiene {} publicaciones",
                    correo, ficha.getTotalPublicaciones());
        } else {
            log.info("Proyección: evento duplicado ignorado para {}", correo);
        }
    }

    public synchronized void alIniciarseEliminacion(String mensaje) {
        String correo = textoRequerido(objectMapper.readTree(mensaje), "correoInstitucional");
        FichaInvestigador ficha = fichaOCrear(correo);
        ficha.marcarEnEliminacion();
        fichaRepository.save(ficha);
        log.info("Proyección: {} marcado EN_ELIMINACION", correo);
    }

    public synchronized void alEliminarseInvestigador(String mensaje) {
        String correo = textoRequerido(objectMapper.readTree(mensaje), "correoInstitucional");
        fichaRepository.deleteById(correo);
        log.info("Proyección: ficha de {} eliminada", correo);
    }

    private FichaInvestigador fichaOCrear(String correo) {
        return fichaRepository.findById(correo).orElseGet(() -> FichaInvestigador.nueva(correo));
    }

    private String textoRequerido(JsonNode evento, String campo) {
        JsonNode valor = evento.get(campo);
        if (valor == null || valor.isNull() || valor.asString().isBlank()) {
            throw new IllegalArgumentException("El evento no contiene el campo requerido: " + campo);
        }
        return valor.asString();
    }

    private int enteroRequerido(JsonNode evento, String campo) {
        JsonNode valor = evento.get(campo);
        if (valor == null || valor.isNull() || !valor.isInt()) {
            throw new IllegalArgumentException("El evento no contiene un entero válido: " + campo);
        }
        return valor.asInt();
    }
}
