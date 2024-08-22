package com.digix.selecao_familias.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LeitorArquivoJson<T> {

	private Logger logger = LoggerFactory.getLogger(LeitorArquivoJson.class);

	public List<T> ler(File arquivo, TypeReference<List<T>> typeReference) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

		logger.info("Buscando arquivo {}", arquivo.getName());
		if (!arquivo.exists()) {
			logger.info("Arquivo {} não encontrado", arquivo.getName());	
			return Collections.emptyList();
		}

		try {
			List<T> lista = objectMapper.readValue(arquivo, typeReference);
			logger.info("Carregados {} objetos do arquivo {}", lista.size(), arquivo.getName());
			return lista;
		} catch (IOException e) {
			throw new IllegalArgumentException("Erro ao deserializar arquivo " + arquivo.getName(), e);
		}
	}
}
