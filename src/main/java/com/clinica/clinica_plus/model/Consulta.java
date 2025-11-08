package com.clinica.clinica_plus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento N:1 com Paciente
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    // Relacionamento N:1 com Médico
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @NotNull(message = "Data e hora são obrigatórias")
    @Future(message = "A data da consulta deve ser futura")
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status = StatusConsulta.AGENDADA;

    private String observacoes;

    private Boolean lembreteEnviado = false;
    public Boolean getLembreteEnviado() {
        return lembreteEnviado;
    }

    public void setLembreteEnviado(Boolean lembreteEnviado) {
        this.lembreteEnviado = lembreteEnviado;
    }
}
