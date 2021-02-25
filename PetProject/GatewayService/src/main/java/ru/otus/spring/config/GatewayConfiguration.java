package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class GatewayConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated();

        /*
        http.csrf().disable()
                .authorizeRequests().antMatchers("/oauth/**", "/oauth/check_token").permitAll()
                .and()
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
                .logout().logoutUrl("/logout");*/


    }




    /*

    http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated();


    http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/oauth/**", "/**").permitAll();



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
    * */

    @Bean
    public RemoteTokenServices remoteTokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8070/oauth/check_token");
        tokenServices.setClientId("user");
        tokenServices.setClientSecret("user");
        return tokenServices;
    }


}
