package com.rica.investigadoresservice.investigadores.aplicacion;

import com.rica.investigadoresservice.investigadores.dominio.CorreoInstitucional;
import com.rica.investigadoresservice.investigadores.dominio.Investigador;
import com.rica.investigadoresservice.investigadores.dominio.InvestigadorFactory;
import com.rica.investigadoresservice.investigadores.puertos.salida.RepositorioInvestigadores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvestigadorServiceTest {

    @Mock
    private RepositorioInvestigadores repositorioInvestigadores;

    @Mock
    private InvestigadorFactory investigadorFactory;

    @Mock
    private PublicadorEventos publicadorEventos;

    @InjectMocks
    private InvestigadorService investigadorService;

    @Test
    void confirmarEliminacionBorraAlInvestigadorYPublicaElEventoEliminado() {
        Investigador enEliminacion = new Investigador(
                1L,
                "Ana Torres",
                new CorreoInstitucional("ana.torres@uptc.edu.co"),
                "GIT-UPTC");
        enEliminacion.marcarEnEliminacion();
        when(repositorioInvestigadores.buscarPorCorreo("ana.torres@uptc.edu.co"))
                .thenReturn(Optional.of(enEliminacion));

        investigadorService.confirmarEliminacion("ana.torres@uptc.edu.co");

        verify(repositorioInvestigadores).eliminar(enEliminacion);
        verify(publicadorEventos).publicar(
                "investigadores.eliminados",
                "ana.torres@uptc.edu.co",
                Map.of("correoInstitucional", "ana.torres@uptc.edu.co"));
    }
}
