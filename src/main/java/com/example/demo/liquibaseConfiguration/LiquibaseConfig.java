package com.example.demo.liquibaseConfiguration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {

    @Value("classpath:changelog.xml") // Location of the ChangeLog file
    private Resource changeLog;

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLog.getFilename());
        return liquibase;
    }
}
