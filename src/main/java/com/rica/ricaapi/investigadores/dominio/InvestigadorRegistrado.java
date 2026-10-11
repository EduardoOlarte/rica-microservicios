package com.rica.ricaapi.investigadores.dominio;

import java.time.Instant;

public record InvestigadorRegistrado(
        String correoInstitucional,
        Instant ocurridoEn) {
}