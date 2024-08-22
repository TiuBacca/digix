package com.digix.selecao_familias.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.type.TypeReference;
import com.digix.selecao_familias.util.LeitorArquivoJson;
import com.digix.selecao_familias.util.ManipuladorArquivo;

public abstract class RepositoryLeituraArquivoBase<T> {

	protected List<T> dados = new ArrayList<>();

	@PostConstruct
	protected void carregarDoArquivo() {
		File arquivo;
		try {
			arquivo = ManipuladorArquivo.obterArquivoJson(getNomeArquivoJson());
			LeitorArquivoJson<T> leitor = new LeitorArquivoJson<>();
			dados = leitor.ler(arquivo, getTypeRefenrence());
			aposCarregarArquivo();
		} catch (IOException e) {
			throw new IllegalStateException("Erro ao carregar arquivo json " + getNomeArquivoJson());
		}
	}

	protected void aposCarregarArquivo() {
	}

	public List<T> getAll() {
		return dados;
	}

	protected abstract String getNomeArquivoJson();

	protected abstract TypeReference<List<T>> getTypeRefenrence();

}
