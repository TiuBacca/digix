package com.digix.selecao_familias.pontuacao.familia;

import com.digix.selecao_familias.domain.Familia;
import com.digix.selecao_familias.domain.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegraDependentes implements RegraPontuacao {

    @Override
    public void aplicar(Familia familia) {
        List<Pessoa> dependentes = familia.getMembros().stream()
                .filter(Pessoa::getDependente)
                .collect(Collectors.toList());
        if (dependentes.size() >= 3) {
            familia.setPontuacao(familia.getPontuacao() + 3);
        } else if (!dependentes.isEmpty()) {
            familia.setPontuacao(familia.getPontuacao() + 2);
        }
    }
}