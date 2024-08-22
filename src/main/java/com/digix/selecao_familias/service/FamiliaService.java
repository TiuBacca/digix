package com.digix.selecao_familias.service;

import com.digix.selecao_familias.domain.Familia;
import com.digix.selecao_familias.domain.Pessoa;
import com.digix.selecao_familias.pontuacao.familia.PontuacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamiliaService {

    @Value("${TAMANHO-MAXIMO-FAMILIA}")
    private Integer tamanhoMaximoFamilia;

    @Value("${QUANTIDADE-CASAS-DISPONIVEIS}")
    private Integer qtdCasasDisponiveis;

    private final PessoaService pessoaService;
    private final PontuacaoService pontuacaoService;

    public void realizarConcurso(){
        List<Familia> familias = geraListaFamilia();
        divulgaFamiliasVencedoras(familias);
        //divulgaParticipantes(familias); // caso seja necessário exibir todas familias
    }

    public List<Familia> geraListaFamilia() {
        List<Pessoa> pessoas = pessoaService.getAll();
        return criarFamiliasAleatorias(pessoas);
    }

    public List<Familia> criarFamiliasAleatorias(List<Pessoa> pessoas) {
        List<Familia> familias = new ArrayList<>();

        while (pessoas != null && !pessoas.isEmpty()) {
            Pessoa membroObrigatorio = pessoas.stream()
                    .filter(pessoa -> !pessoa.getDependente())
                    .findFirst()
                    .orElse(null);

            List<Pessoa> membros = new ArrayList<>();

            if (membroObrigatorio != null) {
                membros.add(membroObrigatorio);
                pessoas.remove(membroObrigatorio);
            } else {
                break;
            }

            int numPessoasDisponiveis = pessoas.size();
            int tamanhoFamilia = 1;



            if (numPessoasDisponiveis > 0 && numPessoasDisponiveis != 1) {
                int incremento = ThreadLocalRandom.current().nextInt(0, Math.min(numPessoasDisponiveis, (tamanhoMaximoFamilia != null ? tamanhoMaximoFamilia : 5) - tamanhoFamilia));
                tamanhoFamilia += incremento;

                List<Pessoa> membrosAdicionais = ThreadLocalRandom.current()
                        .ints(0, numPessoasDisponiveis)
                        .distinct()
                        .limit(tamanhoFamilia - 1)
                        .mapToObj(pessoas::get)
                        .collect(Collectors.toList());

                membros.addAll(membrosAdicionais);
                membrosAdicionais.forEach(pessoas::remove);


                if (!validaExistenciaDeIdependentes(pessoas)) {
                    membros.addAll(pessoas);
                }
            }

            if (!membros.isEmpty()) {
                Familia familia = new Familia();
                familia.setId(familias.size() + 1);
                familia.setMembros(membros);
                pontuacaoService.aplicarRegras(familia);
                familias.add(familia);
            }
        }

        return familias;
    }

    public void ordenaFamiliasPelaPontuacao(List<Familia> familias) {
        familias.sort(Comparator.comparing(Familia::getPontuacao).reversed());
    }

    private void divulgaFamiliasVencedoras(List<Familia> familias){
        ordenaFamiliasPelaPontuacao(familias);
        List<Familia> vencedores = familias.subList(0, qtdCasasDisponiveis);
        // Não foi definido um critério de desempate, então a sorte será.
        System.out.println("Número de famílias vencedoras: " + qtdCasasDisponiveis);
        vencedores.forEach( f -> System.out.println(f.toString()));
    }

    private void divulgaParticipantes(List<Familia> familias){
        familias.forEach( f -> System.out.println(f.toString()));
    }

    private Boolean validaExistenciaDeIdependentes(List<Pessoa> pessoas) {
        return pessoas.stream()
                .filter(pessoa -> !pessoa.getDependente())
                .findFirst()
                .orElse(null) != null;
    }

}


