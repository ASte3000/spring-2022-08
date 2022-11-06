package com.stquiz.config;

import com.stquiz.dao.QuizDao;
import com.stquiz.dao.QuizDaoImpl;
import com.stquiz.output.QuizPrintService;
import com.stquiz.output.QuizPrintServiceImpl;
import com.stquiz.service.QuizService;
import com.stquiz.service.QuizServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public QuizService quizService(QuizDao dao, QuizPrintService printService) {
        return new QuizServiceImpl(dao, printService);
    }

    @Bean
    public QuizDao quizDao() {
        return new QuizDaoImpl("QuizElements.csv");
    }

    @Bean
    public QuizPrintService quizPrintService() {
        return new QuizPrintServiceImpl(System.out);
    }

}
