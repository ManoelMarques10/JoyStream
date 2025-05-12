package com.projetofaculdade.projetofaculdade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * É o ponto de entrada da aplicação e contém o método main, que inicia o Spring.
 *
 * A anotação @SpringBootApplication habilita:
 * - @Configuration: permite definir beans de configuração
 * - @EnableAutoConfiguration: ativa a configuração automática do Spring
 * - @ComponentScan: faz a varredura automática de componentes na mesma hierarquia de pacotes
 */
@SpringBootApplication
public class ProjetoFaculdadeApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 *
	 * @param args argumentos de linha de comando (se houver)
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProjetoFaculdadeApplication.class, args);
	}

}