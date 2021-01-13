package ru.otus.spring.integration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.spring.integration.InitApp;

@SuppressWarnings({ "resource", "Duplicates", "InfiniteLoopStatement" })
@ComponentScan
@EnableIntegration
@IntegrationComponentScan
public class App {

    public static void main(String[] args) throws InterruptedException {
        new InitApp().run();
    }

}
