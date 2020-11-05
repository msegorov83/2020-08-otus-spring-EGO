package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.runner.InitApp;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Main {

    private static AppProps appProps;

    public Main(AppProps appProps) {
        this.appProps = appProps;
    }

    public static void main(String[] args) throws Exception {

        var context = SpringApplication.run(Main.class, args);

        new InitApp(appProps).run();

    }



}