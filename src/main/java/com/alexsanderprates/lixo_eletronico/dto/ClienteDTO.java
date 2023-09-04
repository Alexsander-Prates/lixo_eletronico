package com.alexsanderprates.lixo_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ClienteDTO {

    @NotBlank
    private String nome;

    private String cpf;

    private String endereco;
    @Size(max = 9,message = "No máximo 9 números")
    private String telefone;
    @Email
    private String email;
    private Double valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;

    private Long coletora;
}
