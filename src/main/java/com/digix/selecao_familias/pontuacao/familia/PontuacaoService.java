package com.digix.selecao_familias.pontuacao.familia;

import com.digix.selecao_familias.domain.Familia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontuacaoService {

    private List<RegraPontuacao> regras;

    public PontuacaoService (List<RegraPontuacao> regras){
        this.regras = regras;
    }

    public void aplicarRegras(Familia familia) {
        for (RegraPontuacao regra : regras) {
            regra.aplicar(familia);
        }
    }
}