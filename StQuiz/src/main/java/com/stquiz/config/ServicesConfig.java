package com.stquiz.config;

import com.stquiz.dao.QuizDao;
import com.stquiz.dao.QuizDaoImpl;
import com.stquiz.output.QuizPrintService;
import com.stquiz.output.QuizPrintServiceImpl;
import com.stquiz.service.QuizService;
import com.stquiz.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;

@Configuration
@PropertySource("classpath:quiz.properties")
public class ServicesConfig {

    @Bean
    public QuizService quizService(QuizDao dao, QuizPrintService printService) {
        return new QuizServiceImpl(dao, printService);
    }

    @Bean
    public QuizDao quizDao(@Value("${data.resource.key}") String dataResourceKey) {
        return new QuizDaoImpl(dataResourceKey);
    }

    @Bean
    public QuizPrintService quizPrintService(PrintStream printStream) {
        return new QuizPrintServiceImpl(printStream);
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

}
