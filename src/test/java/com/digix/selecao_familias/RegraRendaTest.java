package com.digix.selecao_familias;

import com.digix.selecao_familias.domain.Familia;
import com.digix.selecao_familias.domain.Pessoa;
import com.digix.selecao_familias.pontuacao.familia.RegraRenda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegraRendaTest {

    private RegraRenda regraRenda;

    @BeforeEach
    void setUp() {
        regraRenda = new RegraRenda();
    }


    @Test
    void deveAdicionar5PontosQuandoRendaEhAte900() {
        Familia f = Familia.builder().membros(Collections.singletonList(
                Pessoa.builder().renda(new BigDecimal(900)).build())).pontuacao(0.0).build();

        regraRenda.aplicar(f);
        assertEquals(5, f.getPontuacao());
    }

    @Test
    void deveAdicionar3PontosQuandoRendaEhEntre901E1500() {
        Familia f = Familia.builder().membros(Collections.singletonList(
                Pessoa.builder().renda(new BigDecimal(901)).build())).pontuacao(0.0).build();

        regraRenda.aplicar(f);
        assertEquals(3, f.getPontuacao());

        f = Familia.builder().membros(Collections.singletonList(
                Pessoa.builder().renda(new BigDecimal(1500)).build())).pontuacao(0.0).build();

        regraRenda.aplicar(f);
        assertEquals(3, f.getPontuacao());

    }

    @Test
    void naoDeveAdicionarPontosQuandoRendaEhMaiorQue1500() {
        Familia f = Familia.builder().membros(Collections.singletonList(
                Pessoa.builder().renda(new BigDecimal(1501)).build())).pontuacao(0.0).build();
        regraRenda.aplicar(f);

        assertEquals(0, f.getPontuacao());
    }
}
