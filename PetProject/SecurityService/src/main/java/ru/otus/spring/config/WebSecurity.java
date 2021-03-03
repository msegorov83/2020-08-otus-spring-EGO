package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDataService userDataService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
               .withUser(User.withUsername("user").password("{bcrypt}$2a$10$y1/Nvp1C/UZmVOcZImZ9suXr0H3Y8rvVtnLytW03z44BXBAnza4Fm").roles("USER"))
                .withUser(User.withUsername("admin").password("{bcrypt}$2a$10$KNEJ8Yw9ZbyGzPtC9isP3Ob2/H350HZznKzwzmbhFto/iwnBqJ1Ky").roles("ADMIN"))
               .passwordEncoder(passwordEncoder());

     // auth.userDetailsService(userDataService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

//      return new BCryptPasswordEncoder();
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
