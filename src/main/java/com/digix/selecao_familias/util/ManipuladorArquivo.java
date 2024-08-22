package com.digix.selecao_familias.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ManipuladorArquivo {
	
	private static Logger logger = LoggerFactory.getLogger(ManipuladorArquivo.class);
	
	private ManipuladorArquivo() {
	}

	public static File obterArquivoJson(String nomeArquivoJson) throws IOException {
		Path pathArquivo = Paths.get(".", "dados", nomeArquivoJson);
		if (pathArquivo.toFile().exists()) {
			return pathArquivo.toFile();
		}
		return copiarArquivoDoResouce(nomeArquivoJson, pathArquivo);
	}

	private static File copiarArquivoDoResouce(String nomeArquivoJson, Path pathArquivo) throws IOException {
		logger.info("Obtendo arquivo resource {}", nomeArquivoJson);
		ClassPathResource resource = new ClassPathResource("dados/" +nomeArquivoJson);
		if (!resource.exists()) {
			throw new IllegalStateException(String.format("arquivo %s não encontrado no resource da aplicação", nomeArquivoJson));
		}
		
		logger.info("Copiando arquivo para {}", pathArquivo.toUri());
		Path pathDiretorioDados = pathArquivo.getParent();
		if (!pathDiretorioDados.toFile().exists()) {
			Files.createDirectories(pathDiretorioDados);
		}
		Files.copy(resource.getInputStream(), pathArquivo);
		return pathArquivo.toFile();
	}
}
