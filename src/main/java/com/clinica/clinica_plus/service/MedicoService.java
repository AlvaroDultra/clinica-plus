package com.clinica.clinica_plus.service;

import com.clinica.clinica_plus.model.Medico;
import com.clinica.clinica_plus.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico criarMedico(@Valid Medico medico) {
        if (medicoRepository.findByCrm(medico.getCrm()).isPresent()) {
            throw new IllegalArgumentException("Já existe um médico com este CRM.");
        }

        if (medicoRepository.findByEmail(medico.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um médico com este e-mail.");
        }

        return medicoRepository.save(medico);
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado."));
    }

    public Medico atualizarMedico(Long id, @Valid Medico dadosAtualizados) {
        Medico medico = buscarPorId(id);

        medico.setNome(dadosAtualizados.getNome());
        medico.setCrm(dadosAtualizados.getCrm());
        medico.setEspecialidade(dadosAtualizados.getEspecialidade());
        medico.setEmail(dadosAtualizados.getEmail());
        medico.setTelefone(dadosAtualizados.getTelefone());
        medico.setAtivo(dadosAtualizados.getAtivo());

        return medicoRepository.save(medico);
    }

    public void inativarMedico(Long id) {
        Medico medico = buscarPorId(id);
        medico.setAtivo(false);
        medicoRepository.save(medico);
    }
}
