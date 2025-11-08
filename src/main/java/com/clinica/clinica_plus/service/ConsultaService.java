package com.clinica.clinica_plus.service;

import com.clinica.clinica_plus.model.*;
import com.clinica.clinica_plus.repository.ConsultaRepository;
import com.clinica.clinica_plus.repository.MedicoRepository;
import com.clinica.clinica_plus.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    // 🩺 Agendar consulta
    public Consulta agendarConsulta(@Valid Consulta consulta) {
        Paciente paciente = pacienteRepository.findById(consulta.getPaciente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(consulta.getMedico().getId())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        // Verifica se o médico está ativo
        if (!medico.getAtivo()) {
            throw new IllegalArgumentException("Médico inativo não pode receber agendamentos");
        }

        // Verifica disponibilidade no mesmo horário
        LocalDateTime inicio = consulta.getDataHora().minusMinutes(29);
        LocalDateTime fim = consulta.getDataHora().plusMinutes(29);
        List<Consulta> ocupadas = consultaRepository.findByMedicoAndDataHoraBetween(medico, inicio, fim);

        if (!ocupadas.isEmpty()) {
            throw new IllegalArgumentException("O médico já possui uma consulta nesse horário");
        }

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setStatus(StatusConsulta.AGENDADA);
        consulta.setLembreteEnviado(false);

        return consultaRepository.save(consulta);
    }

    // 📋 Listar todas
    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    // 🔍 Buscar por ID
    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));
    }

    // ❌ Cancelar
    public void cancelarConsulta(Long id) {
        Consulta consulta = buscarPorId(id);

        if (consulta.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível cancelar consultas já realizadas");
        }

        consulta.setStatus(StatusConsulta.CANCELADA);
        consultaRepository.save(consulta);
    }

    // ✅ Concluir
    public void concluirConsulta(Long id) {
        Consulta consulta = buscarPorId(id);
        consulta.setStatus(StatusConsulta.CONCLUIDA);
        consultaRepository.save(consulta);
    }
}
