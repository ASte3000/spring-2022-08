package com.stquiz.config;

import com.stquiz.dao.QuizElementDao;
import com.stquiz.dao.QuizElementDaoImpl;
import com.stquiz.io.IOService;
import com.stquiz.io.ConsoleIOServiceImpl;
import com.stquiz.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
@PropertySource("classpath:quiz.properties")
public class ServicesConfig {

    @Bean
    public QuizElementDao quizDao(@Value("${data.resource.key}") String dataResourceKey) {
        return new QuizElementDaoImpl(dataResourceKey);
    }

    @Bean
    public QuizAnswersChecker quizAnswersChecker(IOService ioService, @Value("${min.pass.score}") int minPassScore) {
        return new QuizAnswersCheckerImpl(ioService, minPassScore);
    }

    @Bean
    public IOService quizIOService() {
        return new ConsoleIOServiceImpl(System.in, System.out);
    }

}
