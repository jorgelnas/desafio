package br.com.duxusdesafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * 
 * <p>Responsável por inicializar e executar a aplicação. A anotação
 * {@code @SpringBootApplication} habilita a configuração automática do Spring,
 * a varredura de componentes e outras funcionalidades padrão do Spring Boot.</p>
 * 
 * <p>Para iniciar a aplicação, execute o método {@code main}.</p>
 * 
 */
@SpringBootApplication
public class DuxusdesafioApplication {

    /**
     * Ponto de entrada da aplicação Spring Boot.
     * 
     * @param args Argumentos da linha de comando (opcional).
     */
    public static void main(String[] args) {
        SpringApplication.run(DuxusdesafioApplication.class, args);
    }

}
