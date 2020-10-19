package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.Main;

@Configuration
public class MainConfig {

    @Bean
    public Main Main(AppProps appProps) {
        return new Main(appProps);
    }
}
