package com.alexsanderprates.lixo_eletronico.repository;

import com.alexsanderprates.lixo_eletronico.entity.Coletora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColetoraRepository extends JpaRepository<Coletora,Long> {

}
