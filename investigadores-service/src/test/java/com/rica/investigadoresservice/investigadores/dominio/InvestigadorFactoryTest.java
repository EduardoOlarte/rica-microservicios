package com.rica.investigadoresservice.investigadores.dominio;

import com.rica.investigadoresservice.investigadores.puertos.salida.RepositorioInvestigadores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvestigadorFactoryTest {

    @Mock
    private RepositorioInvestigadores repositorioInvestigadores;

    @Test
    void creaInvestigadorConDatosDelDominio() {
        when(repositorioInvestigadores.existeCorreo("ana.torres@uptc.edu.co"))
                .thenReturn(false);

        Investigador investigador = new InvestigadorFactory(repositorioInvestigadores)
                .crear("Ana Torres", "ana.torres@uptc.edu.co", "GIT-UPTC");

        assertEquals("Ana Torres", investigador.getNombreCompleto());
        assertEquals("ana.torres@uptc.edu.co",
                investigador.getCorreoInstitucional().valor());
        assertEquals("GIT-UPTC", investigador.getGrupoInvestigacion());
        assertEquals(EstadoInvestigador.ACTIVO, investigador.getEstado());
    }

    @Test
    void rechazaCorreoInstitucionalDuplicado() {
        when(repositorioInvestigadores.existeCorreo("ana.torres@uptc.edu.co"))
                .thenReturn(true);

        assertThrows(CorreoDuplicadoException.class,
                () -> new InvestigadorFactory(repositorioInvestigadores)
                        .crear("Ana Torres", "ana.torres@uptc.edu.co", "GIT-UPTC"));
    }
}
