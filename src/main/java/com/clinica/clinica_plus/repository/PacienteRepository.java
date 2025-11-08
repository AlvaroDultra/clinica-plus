package com.clinica.clinica_plus.repository;

import com.clinica.clinica_plus.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByEmail(String email);
}
