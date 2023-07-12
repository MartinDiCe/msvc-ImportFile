package com.diceprojects.importcsvmeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.diceprojects.importcsvmeli.persistences.repositories")
@EntityScan("com.diceprojects.importcsvmeli.persistences.models")
public class ImportCsvmeliApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImportCsvmeliApplication.class, args);
    }

}
