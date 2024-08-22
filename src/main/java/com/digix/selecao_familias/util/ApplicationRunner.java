package com.digix.selecao_familias.util;

import com.digix.selecao_familias.domain.Familia;
import com.digix.selecao_familias.service.FamiliaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final FamiliaService familiaService;

    public ApplicationRunner(FamiliaService familiaService) {
        this.familiaService = familiaService;
    }

    @Override
    public void run(String... args) {
        familiaService.realizarConcurso();
        //System.exit(0);
    }
}
