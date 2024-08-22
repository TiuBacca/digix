package com.digix.selecao_familias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.digix.selecao_familias")
public class SelecaoFamiliasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelecaoFamiliasApplication.class, args);
    }

}
