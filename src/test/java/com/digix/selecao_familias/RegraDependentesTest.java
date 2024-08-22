package com.digix.selecao_familias;

import com.digix.selecao_familias.domain.Familia;
import com.digix.selecao_familias.domain.Pessoa;
import com.digix.selecao_familias.pontuacao.familia.RegraDependentes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegraDependentesTest {

    private RegraDependentes regraDependentes;

    @BeforeEach
    void setUp() {
        regraDependentes = new RegraDependentes();
    }

    @Test
    void deveAdicionar3PontosQuandoHa3OuMaisDependentes() {
        List<Pessoa> membros = Arrays.asList(Pessoa.builder().dependente(true).build(),
                Pessoa.builder().dependente(true).build(),
                Pessoa.builder().dependente(true).build());

        Familia familia = new Familia();
        familia.setMembros(membros);

        regraDependentes.aplicar(familia);
        assertEquals(3, familia.getPontuacao());

        List<Pessoa> membros2 = Arrays.asList(Pessoa.builder().dependente(true).build(),
                Pessoa.builder().dependente(true).build(),
                Pessoa.builder().dependente(true).build(),
                Pessoa.builder().dependente(true).build(),
                Pessoa.builder().dependente(true).build());

        Familia familia2 = new Familia();
        familia2.setMembros(membros2);
        regraDependentes.aplicar(familia2);
        assertEquals(3, familia2.getPontuacao());

    }

    @Test
    void deveAdicionar2PontosQuandoHa1Ou2Dependentes() {
        List<Pessoa> membros = Arrays.asList(Pessoa.builder().dependente(true).build(), Pessoa.builder().dependente(true).build());
        Familia familia = new Familia();
        familia.setMembros(membros);

        regraDependentes.aplicar(familia);
        assertEquals(2, familia.getPontuacao());
    }

    @Test
    void naoDeveAdicionarPontosQuandoNaoHaDependentes() {
        List<Pessoa> membros = Collections.singletonList(Pessoa.builder().dependente(false).build());
        Familia familia = new Familia();
        familia.setMembros(membros);

        regraDependentes.aplicar(familia);
        assertEquals(0, familia.getPontuacao());
    }

}
