package com.alexsanderprates.lixo_eletronico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean //precisamos configurar class que n sao do projeto como Bean
    public Docket detalhesApi(){
        //
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.infosApi());

    }

    private ApiInfo infosApi(){
        return new ApiInfoBuilder().version("1.0 Beta")
                .title("Lixo Eletrônco ")
                .description("Api Teste - Cadastros e Manipulações DB")
                .contact(getContato())
                .build();
    }
    private Contact getContato(){
        return new Contact("Alexsander","https://byte-site.s3.sa-east-1.amazonaws.com/pages/home.html","alexsander.prates@hotmail.com");
    }
}
