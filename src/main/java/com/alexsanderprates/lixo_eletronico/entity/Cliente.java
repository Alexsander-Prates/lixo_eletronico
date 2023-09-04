package com.alexsanderprates.lixo_eletronico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotBlank
    private String nome;

    private String cpf;

    private String endereco;
    @Size(max = 9,message = "No máximo 9 números")
    private String telefone;
    @Email
    private String email;
    private Double valor;

    private LocalDateTime data;


    @ManyToOne
    @JoinColumn(name = "coletora")
    private Coletora coletora;
}
