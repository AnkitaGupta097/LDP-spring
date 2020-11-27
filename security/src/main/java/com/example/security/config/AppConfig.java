package com.example.security.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@Slf4j
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    DataSource getDataSource() throws PropertyVetoException {

        // create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // set the jdbc driver class


        securityDataSource.setDriverClass(env.getProperty("spring.datasource.driver-class-name"));


        log.info(">>> jdbc.url=" + env.getProperty("spring.datasource.url"));
        log.info(">>> jdbc.user=" + env.getProperty("spring.datasource.username"));


        // set database connection props

        securityDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        securityDataSource.setUser(env.getProperty("spring.datasource.username"));
        securityDataSource.setPassword(env.getProperty("spring.datasource.password"));



        return securityDataSource;
    }
}
