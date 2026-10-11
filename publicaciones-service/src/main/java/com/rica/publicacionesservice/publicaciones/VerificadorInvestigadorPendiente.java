package com.rica.publicacionesservice.publicaciones;

import org.springframework.stereotype.Component;

@Component
public class VerificadorInvestigadorPendiente implements VerificadorInvestigador {

    @Override
    public boolean existe(String correoInstitucional) {
        return true;
    }
}