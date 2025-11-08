package com.clinica.clinica_plus.controller;

import com.clinica.clinica_plus.model.Medico;
import com.clinica.clinica_plus.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> criarMedico(@Valid @RequestBody Medico medico) {
        Medico salvo = medicoService.criarMedico(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        return ResponseEntity.ok(medicoService.listarMedicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizarMedico(@PathVariable Long id, @Valid @RequestBody Medico medico) {
        return ResponseEntity.ok(medicoService.atualizarMedico(id, medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarMedico(@PathVariable Long id) {
        medicoService.inativarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
