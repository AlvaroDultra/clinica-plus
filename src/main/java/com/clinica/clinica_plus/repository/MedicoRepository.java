package com.clinica.clinica_plus.repository;

import com.clinica.clinica_plus.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByCrm(String crm);
    Optional<Medico> findByEmail(String email);
}
