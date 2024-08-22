package com.digix.selecao_familias.pontuacao.familia;

import com.digix.selecao_familias.domain.Familia;
import org.springframework.stereotype.Service;

@Service
public class RegraRenda implements RegraPontuacao {

    @Override
    public void aplicar(Familia familia) {
        if (familia.getRendaFamiliar().doubleValue() <= 900) {
            familia.setPontuacao(familia.getPontuacao() + 5);
        } else if (familia.getRendaFamiliar().doubleValue() <= 1500) {
            familia.setPontuacao(familia.getPontuacao() + 3);
        }
    }
}