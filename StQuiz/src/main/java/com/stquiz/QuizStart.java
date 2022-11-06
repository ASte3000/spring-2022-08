package com.stquiz;

import com.stquiz.service.QuizService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
public class QuizStart {
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizStart.class);
        QuizService service = context.getBean(QuizService.class);
        service.printElements();
    }
}
