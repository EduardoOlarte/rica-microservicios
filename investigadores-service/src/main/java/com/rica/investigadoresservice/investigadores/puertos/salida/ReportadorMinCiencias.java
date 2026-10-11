package com.rica.investigadoresservice.investigadores.puertos.salida;

import com.rica.investigadoresservice.investigadores.dominio.Investigador;

public interface ReportadorMinCiencias {

    void enviar(Investigador investigador);
}