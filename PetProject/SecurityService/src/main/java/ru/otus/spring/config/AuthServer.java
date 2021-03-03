package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class AuthServer extends AuthorizationServerConfigurerAdapter  {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients.inMemory().withClient("user").secret(passwordEncoder.encode("user")).authorizedGrantTypes("refresh_token","password")
       .scopes("webclient","mobileclient");

       // clients.jdbc(dataSource).withClient("1").authorizedGrantTypes("refresh_token","password").scopes("webclient","mobileclient");
     //   auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
     //           .withUser(User.withUsername("user").password("{bcrypt}$2a$10$y1/Nvp1C/UZmVOcZImZ9suXr0H3Y8rvVtnLytW03z44BXBAnza4Fm").roles("USER"))
        //        .withUser(User.withUsername("admin").password("{bcrypt}$2a$10$KNEJ8Yw9ZbyGzPtC9isP3Ob2/H350HZznKzwzmbhFto/iwnBqJ1Ky").roles("ADMIN"))
          //      .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }


}
