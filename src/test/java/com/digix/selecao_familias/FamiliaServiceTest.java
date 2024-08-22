package com.digix.selecao_familias;

import com.digix.selecao_familias.domain.Familia;
import com.digix.selecao_familias.pontuacao.familia.PontuacaoService;
import com.digix.selecao_familias.pontuacao.familia.RegraDependentes;
import com.digix.selecao_familias.pontuacao.familia.RegraPontuacao;
import com.digix.selecao_familias.pontuacao.familia.RegraRenda;
import com.digix.selecao_familias.service.FamiliaService;
import com.digix.selecao_familias.service.PessoaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FamiliaServiceTest {

    private static FamiliaService familiaService;

    @BeforeAll
    static void setUp() {
        List<RegraPontuacao> regras = Arrays.asList(new RegraRenda(), new RegraDependentes());
        familiaService = new FamiliaService(new PessoaService(), new PontuacaoService(regras));
    }

    @Test
    void testGeraListaFamilia() {
        List<Familia> familias = familiaService.geraListaFamilia();
        assertFalse(familias.isEmpty());
    }

}
