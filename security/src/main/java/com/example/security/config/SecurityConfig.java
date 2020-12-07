package com.example.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);

//                .inMemoryAuthentication().withUser("ankita")
//                .password("{noop}ankita098").roles("EMPLOYEE")
//                .and()
//                .withUser("admin").password("{noop}admin").roles("EMPLOYEE", "ADMIN")
//                .and()
//                .withUser("manager").password("{noop}manager").roles("EMPLOYEE", "MANAGER");

    }
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers( "/managers/**").hasRole("MANAGER")
                .antMatchers("/admins/**").hasRole("ADMIN")
                .antMatchers("/abc").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");




    }



}