package com.clinica.clinica_plus.controller;

import com.clinica.clinica_plus.model.Consulta;
import com.clinica.clinica_plus.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendar(@Valid @RequestBody Consulta consulta) {
        Consulta nova = consultaService.agendarConsulta(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Void> concluir(@PathVariable Long id) {
        consultaService.concluirConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
