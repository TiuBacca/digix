package com.digix.selecao_familias.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pessoa {
    private Long id;
    private String nome;
    private int idade;
    private Boolean dependente;
    private BigDecimal renda;

    public Pessoa(Long id, String nome, int idade, BigDecimal renda) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.renda = renda;
    }

    @Override
    public String toString() {
        return "\n nome: " + this.nome
                + " - idade: " + this.idade
                + " - renda: " + this.renda
                + " - dependente: " + this.dependente;
    }
}
