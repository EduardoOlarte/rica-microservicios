package com.rica.ricaapi.investigadores;

import com.rica.ricaapi.investigadores.dominio.Investigador;
import com.rica.ricaapi.investigadores.puertos.entrada.InvestigadorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investigadores")
public class InvestigadorController {

    private final InvestigadorUseCase investigadorService;

    public InvestigadorController(InvestigadorUseCase investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping
    public List<InvestigadorResponse> listar() {
        return investigadorService.listarTodos().stream()
                .map(InvestigadorMapper::aResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public InvestigadorResponse buscarPorId(@PathVariable Long id) {
        Investigador investigador = investigadorService.buscarPorId(id);
        return InvestigadorMapper.aResponse(investigador);
    }

    @PostMapping
    public ResponseEntity<InvestigadorResponse> registrar(
            @Valid @RequestBody InvestigadorRequest request) {

        Investigador investigador = investigadorService.registrar(
                request.getNombreCompleto(),
                request.getCorreoInstitucional(),
                request.getGrupoInvestigacion()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InvestigadorMapper.aResponse(investigador));
    }
}