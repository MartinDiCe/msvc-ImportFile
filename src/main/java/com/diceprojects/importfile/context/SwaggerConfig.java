package com.diceprojects.importfile.context;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración personalizada de Swagger para msvc-import.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura la información de la API para Swagger.
     *
     * @return Objeto OpenAPI configurado.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("v0.0.1")
                        .description("Documentation msvc-import")
                        .termsOfService("https://diceprojects.com/termOfService")
                        .license(new License().name("diceLicense").url("https://diceprojects/api/license")));
    }

}

