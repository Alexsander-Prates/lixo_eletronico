package com.alexsanderprates.lixo_eletronico.dto;

import lombok.Data;

@Data
public class ColetoraDTO {

    private Long codigo;

    private String empresa;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;
    private String produto;
    private Double pagamento;

    private Long cliente;

}
