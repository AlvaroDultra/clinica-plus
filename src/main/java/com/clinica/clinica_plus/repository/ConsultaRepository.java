package com.clinica.clinica_plus.repository;

import com.clinica.clinica_plus.model.Consulta;
import com.clinica.clinica_plus.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByMedicoAndDataHoraBetween(Medico medico, LocalDateTime inicio, LocalDateTime fim);

}
