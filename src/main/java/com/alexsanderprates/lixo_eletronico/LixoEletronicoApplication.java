package com.alexsanderprates.lixo_eletronico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LixoEletronicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LixoEletronicoApplication.class, args);
	}

}
