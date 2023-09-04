package com.alexsanderprates.lixo_eletronico.repository;

import com.alexsanderprates.lixo_eletronico.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query(value = "Select c from Cliente c where c.nome like ?1")
    List<Cliente> buscarNomeCliente(String nome);
}
