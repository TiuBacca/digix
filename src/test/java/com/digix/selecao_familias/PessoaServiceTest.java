package com.digix.selecao_familias;

import com.digix.selecao_familias.domain.Pessoa;
import com.digix.selecao_familias.service.PessoaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PessoaServiceTest {

    private static PessoaService pessoaService;
    private static List<Pessoa> pessoas;

    @BeforeAll
    static void setUp() {
        pessoaService = new PessoaService();
        pessoas = pessoaService.getAll();
    }

    @Test
    void testAjustaDependenciaIdade() {
        pessoaService.ajustaDependenciaIdade(pessoas);

        List<Pessoa> dependentes = pessoas.stream().filter(p -> p.getIdade() < 18).collect(Collectors.toList());
        List<Pessoa> idependentes = pessoas.stream().filter(p -> p.getIdade() >= 18).collect(Collectors.toList());

        dependentes.forEach(d -> assertTrue(d.getDependente()));
        idependentes.forEach(d -> assertFalse(d.getDependente()));
    }

    @Test
    void testBuscaListaPessoas() {
        assertFalse(pessoas.isEmpty());
    }
}
