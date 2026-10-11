package com.rica.ricaapi.investigadores;

import com.rica.ricaapi.investigadores.dominio.Investigador;

public class InvestigadorMapper {

    private InvestigadorMapper() {
    }

    public static InvestigadorResponse aResponse(Investigador investigador) {
        return new InvestigadorResponse(
                investigador.getId(),
                investigador.getNombreCompleto(),
                investigador.getGrupoInvestigacion()
        );
    }
}