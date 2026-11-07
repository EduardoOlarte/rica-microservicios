package com.rica.investigadoresservice.investigadores.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvestigadorTest {

    @Test
    void correoInstitucionalSoloAceptaDominioUptc() {
        assertThrows(IllegalArgumentException.class,
                () -> new CorreoInstitucional("ana.torres@gmail.com"));
    }

    @Test
    void marcarEnEliminacionCambiaElEstado() {
        Investigador investigador = new Investigador(
                1L,
                "Ana Torres",
                new CorreoInstitucional("ana.torres@uptc.edu.co"),
                "GIT-UPTC");

        investigador.marcarEnEliminacion();

        assertEquals(EstadoInvestigador.EN_ELIMINACION, investigador.getEstado());
        assertEquals(true, investigador.estaEnEliminacion());
    }
}
