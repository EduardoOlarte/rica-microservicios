package com.rica.consultasservice.consultas;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RegistroEventosProcesados {

    private final MongoTemplate mongoTemplate;

    public RegistroEventosProcesados(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public boolean primeraVez(String idEvento) {
        try {
            mongoTemplate.insert(new EventoProcesado(idEvento, Instant.now()));
            return true;
        } catch (DuplicateKeyException yaExiste) {
            return false;
        }
    }
}
