package ru.otus.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").anonymous()
                .and()
                .authorizeRequests().antMatchers("/edit", "/add", "/api/delete",
                                                            "/authors/edit", "/authors/add", "/api/authors/delete",
                                                            "/genres/edit", "/genres/add", "/api/genres/delete").hasRole("ADMIN")
                .and()
                    .authorizeRequests().antMatchers("/authors", "/genres" ).hasAnyRole("ADMIN", "USER")
                .and()
                    .formLogin()
                    .usernameParameter("books_user")
                    .passwordParameter("books_password")
                .failureForwardUrl("/error")
                .and()
                .logout().logoutUrl("/logout");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
                .withUser(User.withUsername("user").password("{bcrypt}$2a$10$y1/Nvp1C/UZmVOcZImZ9suXr0H3Y8rvVtnLytW03z44BXBAnza4Fm").roles("USER"))
                .withUser(User.withUsername("admin").password("{bcrypt}$2a$10$KNEJ8Yw9ZbyGzPtC9isP3Ob2/H350HZznKzwzmbhFto/iwnBqJ1Ky").roles("ADMIN"))
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}

//PasswordEncoder passwordEncoder = passwordEncoder();
//String encoded = passwordEncoder.encode("admin");
//System.out.println("admin = " + encoded );
