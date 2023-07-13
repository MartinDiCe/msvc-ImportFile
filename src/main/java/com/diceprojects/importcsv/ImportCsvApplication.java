package com.diceprojects.importcsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.diceprojects.importcsv.persistences.repositories")
@EntityScan("com.diceprojects.importcsv.persistences.models")
public class ImportCsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImportCsvApplication.class, args);
    }

}