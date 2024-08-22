package com.digix.selecao_familias.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.digix.selecao_familias.domain.Pessoa;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(value= BeanDefinition.SCOPE_SINGLETON)
public class PessoaRepository extends RepositoryLeituraArquivoBase<Pessoa>{

    @Override
    protected String getNomeArquivoJson() {
        return "pessoa.json";
    }

    @Override
    protected TypeReference<List<Pessoa>> getTypeRefenrence() {
        return new TypeReference<List<Pessoa>>() {
        };
    }

    public PessoaRepository (){
        carregarDoArquivo();
    }


}