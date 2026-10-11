package com.rica.ricaapi.investigadores.adaptadores.salida;

import com.rica.ricaapi.investigadores.dominio.Investigador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestigadorRepository
        extends JpaRepository<Investigador, Long> {

    boolean existsByCorreoInstitucional_Valor(String valor);

    Optional<Investigador> findByCorreoInstitucional_Valor(String valor);
}