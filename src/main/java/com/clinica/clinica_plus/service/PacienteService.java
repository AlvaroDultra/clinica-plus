package com.clinica.clinica_plus.service;

import com.clinica.clinica_plus.model.Paciente;
import com.clinica.clinica_plus.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;


    public Paciente criarPaciente(@Valid Paciente paciente) {
        if (pacienteRepository.findByCpf(paciente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente com este CPF.");
        }

        if (pacienteRepository.findByEmail(paciente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente com este e-mail.");
        }

        return pacienteRepository.save(paciente);
    }


    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }


    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
    }


    public Paciente atualizarPaciente(Long id, @Valid Paciente dadosAtualizados) {
        Paciente paciente = buscarPorId(id); // reutiliza o método acima

        paciente.setNome(dadosAtualizados.getNome());
        paciente.setCpf(dadosAtualizados.getCpf());
        paciente.setEmail(dadosAtualizados.getEmail());
        paciente.setTelefone(dadosAtualizados.getTelefone());
        paciente.setAtivo(dadosAtualizados.getAtivo());

        return pacienteRepository.save(paciente);
    }


    public void inativarPaciente(Long id) {
        Paciente paciente = buscarPorId(id);
        paciente.setAtivo(false);
        pacienteRepository.save(paciente);
    }
}
