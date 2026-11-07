package com.rica.consultasservice.consultas;

import java.util.List;

public record FichaResponse(
        String correo,
        String nombreCompleto,
        String estado,
        List<ResumenPublicacion> publicaciones,
        int totalPublicaciones) {

    public static FichaResponse desde(FichaInvestigador ficha) {
        return new FichaResponse(
                ficha.getCorreo(),
                ficha.getNombreCompleto(),
                ficha.getEstado(),
                List.copyOf(ficha.getPublicaciones()),
                ficha.getTotalPublicaciones());
    }
}
