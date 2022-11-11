package com.stquiz.config;

import com.stquiz.dao.QuizDao;
import com.stquiz.dao.QuizDaoImpl;
import com.stquiz.io.IOService;
import com.stquiz.io.ConsoleIOServiceImpl;
import com.stquiz.service.QuizService;
import com.stquiz.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:quiz.properties")
public class ServicesConfig {

    @Bean
    public QuizService quizService(QuizDao dao, IOService ioService, @Value("${min.pass.score}") int minPassScore) {
        return new QuizServiceImpl(dao, ioService, minPassScore);
    }

    @Bean
    public QuizDao quizDao(@Value("${data.resource.key}") String dataResourceKey) {
        return new QuizDaoImpl(dataResourceKey);
    }

    @Bean
    public IOService quizIOService() {
        return new ConsoleIOServiceImpl();
    }

}
