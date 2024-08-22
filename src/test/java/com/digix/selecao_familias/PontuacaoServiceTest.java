package com.digix.selecao_familias;

import com.digix.selecao_familias.domain.Familia;
import com.digix.selecao_familias.domain.Pessoa;
import com.digix.selecao_familias.pontuacao.familia.PontuacaoService;
import com.digix.selecao_familias.pontuacao.familia.RegraDependentes;
import com.digix.selecao_familias.pontuacao.familia.RegraPontuacao;
import com.digix.selecao_familias.pontuacao.familia.RegraRenda;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PontuacaoServiceTest {

    private static PontuacaoService pontuacaoService;

    @BeforeAll
    static void setUp() {
        List<RegraPontuacao> regras = Arrays.asList(new RegraRenda(), new RegraDependentes());
        pontuacaoService = new PontuacaoService(regras);
    }

    @Test
    void deveAplicarTodasAsRegrasCorretamente() {
        Familia f = Familia.builder().membros(Arrays.asList(
                Pessoa.builder().dependente(true).renda(BigDecimal.ZERO).build(),
                Pessoa.builder().dependente(true).renda(new BigDecimal(900)).build())).pontuacao(0.0).build();

        pontuacaoService.aplicarRegras(f);

        assertEquals(7, f.getPontuacao());
    }
}
