package com.stquiz;

import com.stquiz.service.QuizService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuizStart {
    public static void main(String... args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService service = context.getBean(QuizService.class);
        service.printElements();
    }
}
