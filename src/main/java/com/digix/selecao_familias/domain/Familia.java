package com.digix.selecao_familias.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Familia {

    private int id;
    private List<Pessoa> membros;
    private Double pontuacao = 0.0;

    public BigDecimal getRendaFamiliar() {
        return membros.stream()
                .map(Pessoa::getRenda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return  "ID: " + this.id
                +  " - Quantidade membros:" + this.membros.size()
                + " - Membros: " + this.membros.stream()
                .map(Pessoa::toString)
                .collect(Collectors.joining(", \n")) + "\n"
                + " - Renda total: " + this.getRendaFamiliar()
                + " - Pontuacao: " + this.pontuacao
                + " - Dependentes: " + this.membros.stream()
                .filter(Pessoa::getDependente)
                .count() + "\n";
    }
}
