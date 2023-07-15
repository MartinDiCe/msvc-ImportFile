package com.diceprojects.importcsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringBootApplication
@EnableFeignClients(basePackages = "com.diceprojects.importcsv.clients")
@EnableJpaRepositories("com.diceprojects.importcsv.persistences.repositories")
@EntityScan("com.diceprojects.importcsv.persistences.models.entities")
@EnableCaching
public class ImportCsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImportCsvApplication.class, args);
    }

}