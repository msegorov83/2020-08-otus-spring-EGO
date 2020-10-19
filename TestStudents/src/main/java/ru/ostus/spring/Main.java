package ru.ostus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.ostus.spring.service.QuestionService;


public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService questionService = applicationContext.getBean(QuestionService.class);
        questionService.setResource();
        questionService.showTest();

    }

}


