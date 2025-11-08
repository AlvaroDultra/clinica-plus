package com.clinica.clinica_plus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                     // Diz ao JPA que esta classe é uma tabela
@Table(name = "pacientes")  // Nome da tabela no banco
@Data                       // Lombok: cria getters, setters, equals, hashCode e toString
@NoArgsConstructor           // Construtor vazio (obrigatório para JPA)
@AllArgsConstructor          // Construtor completo
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    @Column(unique = true, nullable = false)
    private String cpf;

    @Email(message = "E-mail inválido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    private Boolean ativo = true; // inativação lógica
}
