package com.alexsanderprates.lixo_eletronico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coletora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String empresa;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;
    private Double pagamento;

    @JsonIgnore
    @OneToMany(mappedBy = "coletora")
    private List<Cliente> cliente;

}