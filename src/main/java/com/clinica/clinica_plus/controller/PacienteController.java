package com.clinica.clinica_plus.controller;

import com.clinica.clinica_plus.model.Paciente;
import com.clinica.clinica_plus.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> criarPaciente(@Valid @RequestBody Paciente paciente) {
        Paciente salvo = pacienteService.criarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.atualizarPaciente(id, paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarPaciente(@PathVariable Long id) {
        pacienteService.inativarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
