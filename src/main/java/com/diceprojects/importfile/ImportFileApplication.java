package com.diceprojects.importfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal de la aplicación ImportFileApplication.
 */
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringBootApplication
@EnableFeignClients(basePackages = "com.diceprojects.importfile.clients")
@EnableJpaRepositories("com.diceprojects.importfile.persistences.repositories")
@EntityScan("com.diceprojects.importfile.persistences.models.entities")
@EnableCaching
public class ImportFileApplication {

    /**
     * Método principal que inicia la aplicación ImportFileApplication.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(ImportFileApplication.class, args);
    }

}