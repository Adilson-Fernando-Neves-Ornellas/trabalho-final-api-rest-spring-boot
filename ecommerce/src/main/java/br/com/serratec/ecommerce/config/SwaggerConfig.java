package br.com.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/swagger-ui/")
        .setViewName("forward:/webjars/swagger-ui/index.html");
    }

    @Bean

    public OpenAPI customOpenAPI() {

     return new OpenAPI()

          .info(new Info()

          .title("Aplicação E-commerce/Trabalho de API")

          .version("1.0")

          .description("Trabalho do grupo 1")

          .termsOfService("http://swagger.io/terms/")

          .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }
}
