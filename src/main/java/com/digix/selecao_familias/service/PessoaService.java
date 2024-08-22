package com.digix.selecao_familias.service;

import com.digix.selecao_familias.domain.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.digix.selecao_familias.repository.PessoaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(){
        pessoaRepository = new PessoaRepository();
    }

    public List<Pessoa> getAll(){
        List<Pessoa> pessoas =  pessoaRepository.getAll();
        ajustaDependenciaIdade(pessoas);
        return pessoas;
    }

    public void ajustaDependenciaIdade(List<Pessoa> pessoas){
        pessoas.forEach(pessoa -> {
            pessoa.setDependente( pessoa.getIdade() < 18 );
        });
    }
}
