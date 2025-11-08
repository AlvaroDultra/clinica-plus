package com.clinica.clinica_plus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Pattern(regexp = "\\d{6,7}", message = "CRM deve conter 6 ou 7 dígitos numéricos")
    @Column(unique = true, nullable = false)
    private String crm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especialidade especialidade;

    @Email(message = "E-mail inválido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    private Boolean ativo = true;
}
