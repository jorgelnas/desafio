package br.com.duxusdesafio;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Classe responsável por configurar a aplicação quando ela é empacotada
 * como um WAR e implantada em um servidor de aplicação (como Tomcat, JBoss, etc).
 * 
 * <p>Extende {@link SpringBootServletInitializer} para permitir a inicialização da
 * aplicação Spring Boot em ambientes servlet tradicionais.</p>
 * 
 * <p>O método {@code configure} especifica a classe principal da aplicação
 * ({@link DuxusdesafioApplication}) como fonte de configuração.</p>
 * 
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configura a aplicação para execução em um contêiner servlet.
     *
     * @param application Instância do SpringApplicationBuilder.
     * @return O builder configurado com a classe principal da aplicação.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DuxusdesafioApplication.class);
    }

}
