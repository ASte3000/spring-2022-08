package com.stquiz;

import com.stquiz.service.QuizService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class QuizStarter {
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizStarter.class);
        QuizService service = context.getBean(QuizService.class);
        service.runQuiz();
    }
}
