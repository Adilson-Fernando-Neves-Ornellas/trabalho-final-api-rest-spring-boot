package br.com.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    // Define um método bean chamado "productApi" que configura o Swagger Docket
    @Bean
    public Docket productApi() {
        // Cria e configura um objeto Docket para o Swagger 2
        return new Docket(DocumentationType.SWAGGER_2)
            .select()// inicia a configuração do seletor
            .apis(RequestHandlerSelectors.basePackage("br.com.serratec.ecommerce"))//Define o pacote base no qual o Swagger irá procurar por classes de controle para documentação
            .build()
            .apiInfo(metaData());//Define informações da API 
    }

    // Define um método privado chamado "metaData" que configura informações da API
    private ApiInfo metaData() {
        // Cria e configura um objeto ApiInfoBuilder para definir informações da API
        return new ApiInfoBuilder()
            .title("API REST SPRING BOOT - TRBALHO FINAL POO GRUPO 01") // Define o título da API
            .description("Trabalho desenvolvido no serratec") // Define a descrição da API
            .version("1.0.0") // Define a versão da API
            .build(); // Constrói o objeto ApiInfo
    }

    // Sobrescreve o método "addResourceHandlers" para configurar os manipuladores de recursos
    @Override//sobrescreve o pai extends
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configura os manipuladores de recursos para a interface do usuário do Swagger
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        // Configura os manipuladores de recursos para os webjars usados pelo Swagger
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
